package com.ejemplo.articulos.controller;

import com.ejemplo.articulos.model.Pedido;
import com.ejemplo.articulos.model.PedidoDetalle;
import com.ejemplo.articulos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listarPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedidoParcial) {
        return pedidoService.obtenerPedidoPorId(id)
                .map(pedidoExistente -> {
                    if (pedidoParcial.getNombre_cliente() != null) {
                        pedidoExistente.setNombre_cliente(pedidoParcial.getNombre_cliente());
                    }
                    if (pedidoParcial.getDireccion_envio() != null) {
                        pedidoExistente.setDireccion_envio(pedidoParcial.getDireccion_envio());
                    }
                    if (pedidoParcial.getTotal() != null) {
                        pedidoExistente.setTotal(pedidoParcial.getTotal());
                    }
                    if (pedidoParcial.getEstado() != null) {
                        pedidoExistente.setEstado(pedidoParcial.getEstado());
                    }
                    if (pedidoParcial.getDetalles() != null) {
                        // Elimina todos los detalles actuales y los reemplaza por los nuevos
                        pedidoExistente.getDetalles().clear();
                        for (PedidoDetalle detalle : pedidoParcial.getDetalles()) {
                            detalle.setPedido(pedidoExistente); // Asegura la relación bidireccional
                            pedidoExistente.getDetalles().add(detalle);
                        }
                    }
                    Pedido actualizado = pedidoService.guardarPedido(pedidoExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
