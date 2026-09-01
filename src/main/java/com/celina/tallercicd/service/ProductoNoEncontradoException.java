package com.celina.tallercicd.service;

public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(Long id) {
        super("Producto no encontrado con id: " + id);
    }
}
