package com.ejemplo.articulos.service;

import com.ejemplo.articulos.model.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoService {
    List<Pedido> listarPedidos();
    Optional<Pedido> obtenerPedidoPorId(Long id);
    Pedido guardarPedido(Pedido pedido);
    void eliminarPedido(Long id);
}
