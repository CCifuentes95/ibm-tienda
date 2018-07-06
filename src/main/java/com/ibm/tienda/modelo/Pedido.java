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
@Table(name = "pedido", schema = "public")
public class Pedido implements java.io.Serializable {
    private Long id;
    @NotNull
    private Usuario usuario;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String codigo;
    private String direccion;
    private Date fechaCreacion;
    private Date fechaPedido;
    private Double total;
    private Set<DetallePedido> detallePedidos = new HashSet<DetallePedido>(0);

    public Pedido() {
    }

    public Pedido(Long id, String codigo, Set<DetallePedido> detallePedidos,
        String direccion, Date fechaCreacion, Date fechaPedido, Double total,
        Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
        this.codigo = codigo;
        this.direccion = direccion;
        this.fechaCreacion = fechaCreacion;
        this.fechaPedido = fechaPedido;
        this.total = total;
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
    @JoinColumn(name = "usuario_id")
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name = "codigo", nullable = false)
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "direccion")
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Column(name = "fecha_creacion")
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Column(name = "fecha_pedido")
    public Date getFechaPedido() {
        return this.fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @Column(name = "total")
    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
    public Set<DetallePedido> getDetallePedidos() {
        return this.detallePedidos;
    }

    public void setDetallePedidos(Set<DetallePedido> detallePedidos) {
        this.detallePedidos = detallePedidos;
    }
}
