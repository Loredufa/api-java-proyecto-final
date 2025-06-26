package com.ejemplo.articulos.repository;

import com.ejemplo.articulos.model.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
}
