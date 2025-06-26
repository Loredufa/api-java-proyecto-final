package com.ejemplo.articulos.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Indica que esta clase es una entidad JPA
@Entity
@Table(name = "pedido") // Mapea a la tabla "articulo"
public class Pedido {

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Autoincremental
    private Long id;

    private String nombre_cliente;
    private String direccion_envio;
    private Double total;
    private String estado; // Estado del pedido (pendiente, enviado, entregado, cancelado)

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = jakarta.persistence.FetchType.EAGER)
    private List<PedidoDetalle> detalles;

    public Pedido() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre_cliente() { return nombre_cliente; }
    public void setNombre_cliente(String nombre_cliente) { this.nombre_cliente = nombre_cliente; }
    public String getDireccion_envio() { return direccion_envio; }
    public void setDireccion_envio(String direccion_envio) { this.direccion_envio = direccion_envio; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public List<PedidoDetalle> getDetalles() { return detalles; }
    public void setDetalles(List<PedidoDetalle> detalles) { this.detalles = detalles; }
}
