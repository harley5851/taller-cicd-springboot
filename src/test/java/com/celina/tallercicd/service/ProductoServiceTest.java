package com.celina.tallercicd.service;

import com.celina.tallercicd.model.Producto;
import com.celina.tallercicd.repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductoServiceTest {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void debeCalcularPrecioConDescuento() {
        double resultado = productoService.aplicarDescuento(100.0, 10);
        assertEquals(90.0, resultado);
    }

    @Test
    void debeLanzarErrorSiElDescuentoEsInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> productoService.aplicarDescuento(100.0, 150));
    }

    @Test
    void debeCrearYObtenerProducto() {
        Producto nuevo = new Producto("Extensión Clip-in 20\"", "Cabello 100% natural", 45000, 30);

        Producto guardado = productoService.crearProducto(nuevo);
        assertNotNull(guardado.getId());

        Producto encontrado = productoService.obtenerPorId(guardado.getId());
        assertEquals("Extensión Clip-in 20\"", encontrado.getNombre());
    }

    @Test
    void debeActualizarProducto() {
        Producto guardado = productoService.crearProducto(
                new Producto("Extensión Tape-in", "Cinta adhesiva", 60000, 15));

        Producto actualizado = new Producto("Extensión Tape-in Premium", "Cinta adhesiva reforzada", 65000, 10);
        Producto resultado = productoService.actualizarProducto(guardado.getId(), actualizado);

        assertEquals("Extensión Tape-in Premium", resultado.getNombre());
        assertEquals(65000, resultado.getPrecio());
    }

    @Test
    void debeEliminarProducto() {
        Producto guardado = productoService.crearProducto(
                new Producto("Extensión Micro Ring", "Anillo metálico", 55000, 20));

        productoService.eliminarProducto(guardado.getId());

        Optional<Producto> resultado = productoRepository.findById(guardado.getId());
        assertTrue(resultado.isEmpty());
    }

    @Test
    void debeLanzarErrorSiProductoNoExiste() {
        assertThrows(ProductoNoEncontradoException.class,
                () -> productoService.obtenerPorId(999_999L));
    }
}
