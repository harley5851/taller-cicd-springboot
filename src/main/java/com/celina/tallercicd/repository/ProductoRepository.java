package com.celina.tallercicd.repository;

import com.celina.tallercicd.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
