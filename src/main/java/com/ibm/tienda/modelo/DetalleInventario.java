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
@Table(name = "detalle_inventario", schema = "public")
public class DetalleInventario implements java.io.Serializable {

	private Long id;
    @NotNull
    private Inventario inventario;
    @NotNull
    private Producto producto;
    private Integer cantidad;
    private Double precioBase;

    public DetalleInventario() {
    }

    public DetalleInventario(Long id, Integer cantidad, Inventario inventario,
        Double precioBase, Producto producto) {
        this.id = id;
        this.inventario = inventario;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioBase = precioBase;
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
    @JoinColumn(name = "inventario_id")
    public Inventario getInventario() {
        return this.inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
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

    @Column(name = "precio_base")
    public Double getPrecioBase() {
        return this.precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }
}
