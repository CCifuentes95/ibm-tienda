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
@Table(name = "inventario", schema = "public")
public class Inventario implements java.io.Serializable {
    private Long id;
    @NotNull
    private Empresa empresa;
    private Date fechaCreacion;
    private Set<DetalleInventario> detalleInventarios = new HashSet<DetalleInventario>(0);

    public Inventario() {
    }

    public Inventario(Long id, Set<DetalleInventario> detalleInventarios,
        Empresa empresa, Date fechaCreacion) {
        this.id = id;
        this.empresa = empresa;
        this.fechaCreacion = fechaCreacion;
        this.detalleInventarios = detalleInventarios;
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
    @JoinColumn(name = "empresa_id")
    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Column(name = "fecha_creacion")
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "inventario")
    public Set<DetalleInventario> getDetalleInventarios() {
        return this.detalleInventarios;
    }

    public void setDetalleInventarios(Set<DetalleInventario> detalleInventarios) {
        this.detalleInventarios = detalleInventarios;
    }
}
