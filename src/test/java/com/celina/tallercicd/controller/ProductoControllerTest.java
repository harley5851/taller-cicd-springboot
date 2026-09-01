package com.celina.tallercicd.controller;

import com.celina.tallercicd.model.Producto;
import com.celina.tallercicd.service.ProductoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductoController.class)
class ProductoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductoService productoService;

    @Test
    void debeListarProductos() throws Exception {
        Producto producto = new Producto("Extensión Keratina", "Fusión con keratina", 70000, 12);
        producto.setId(1L);

        given(productoService.listarProductos()).willReturn(List.of(producto));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Extensión Keratina"));
    }

    @Test
    void debeCrearProducto() throws Exception {
        Producto entrada = new Producto("Extensión Ponytail", "Cola de caballo sintética", 30000, 25);
        Producto guardado = new Producto("Extensión Ponytail", "Cola de caballo sintética", 30000, 25);
        guardado.setId(2L);

        given(productoService.crearProducto(any(Producto.class))).willReturn(guardado);

        mockMvc.perform(post("/api/productos")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(2L));
    }

    @Test
    void debeEliminarProducto() throws Exception {
        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isNoContent());
    }
}
