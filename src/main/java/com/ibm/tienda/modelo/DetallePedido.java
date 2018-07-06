package com.ibm.tienda.modelo;

import org.hibernate.validator.constraints.*;

import java.util.Date;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "detalle_pedido", schema = "public")
public class DetallePedido implements java.io.Serializable {
    
    private Long id;
    @NotNull
    private Pedido pedido;
    @NotNull
    private Producto producto;
    private Integer cantidad;
    private Double precio;

    public DetallePedido() {
    }

    public DetallePedido(Long id, Integer cantidad, Pedido pedido,
        Double precio, Producto producto) {
        this.id = id;
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    public Pedido getPedido() {
        return this.pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id")
    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Column(name = "cantidad")
    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "precio")
    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
