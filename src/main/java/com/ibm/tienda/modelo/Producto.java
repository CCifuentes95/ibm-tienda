package com.ibm.tienda.modelo;

import org.hibernate.validator.constraints.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import javax.validation.constraints.*;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
@Entity
@Table(name = "producto", schema = "public")
public class Producto implements java.io.Serializable {
    private Long id;
    @NotNull
    private TipoProducto tipoProducto;
    private String codigo;
    private String nombre;
    private Set<DetalleInventario> detalleInventarios = new HashSet<DetalleInventario>(0);
    private Set<DetallePedido> detallePedidos = new HashSet<DetallePedido>(0);

    public Producto() {
    }

    public Producto(Long id, String codigo,
        Set<DetalleInventario> detalleInventarios,
        Set<DetallePedido> detallePedidos, String nombre,
        TipoProducto tipoProducto) {
        this.id = id;
        this.tipoProducto = tipoProducto;
        this.codigo = codigo;
        this.nombre = nombre;
        this.detalleInventarios = detalleInventarios;
        this.detallePedidos = detallePedidos;
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
    @JoinColumn(name = "tipo_producto_id")
    public TipoProducto getTipoProducto() {
        return this.tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Column(name = "codigo")
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
    public Set<DetalleInventario> getDetalleInventarios() {
        return this.detalleInventarios;
    }

    public void setDetalleInventarios(Set<DetalleInventario> detalleInventarios) {
        this.detalleInventarios = detalleInventarios;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
    public Set<DetallePedido> getDetallePedidos() {
        return this.detallePedidos;
    }

    public void setDetallePedidos(Set<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
}
