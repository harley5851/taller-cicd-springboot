package com.celina.tallercicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstadoController {

    @GetMapping("/api/estado")
    public String estado() {
        return "Servicio funcionando correctamente";
    }
}
