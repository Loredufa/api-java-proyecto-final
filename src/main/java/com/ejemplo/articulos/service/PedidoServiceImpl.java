package com.ejemplo.articulos.service;

import com.ejemplo.articulos.model.Pedido;
import com.ejemplo.articulos.model.PedidoDetalle;
import com.ejemplo.articulos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        if (pedido.getDetalles() != null) {
            for (PedidoDetalle detalle : pedido.getDetalles()) {
                detalle.setPedido(pedido);
            }
        }
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
