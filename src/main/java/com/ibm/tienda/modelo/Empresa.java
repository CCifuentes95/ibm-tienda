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
@Table(name = "empresa", schema = "public")
public class Empresa implements java.io.Serializable {
    private Long id;
    private String contacto;
    private String descripcion;
    private String imagen;
    private String nombre;
    private Set<Inventario> inventarios = new HashSet<Inventario>(0);

    public Empresa() {
    }

    public Empresa(Long id, String contacto, String descripcion, String imagen,
        Set<Inventario> inventarios, String nombre) {
        this.id = id;
        this.contacto = contacto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.nombre = nombre;
        this.inventarios = inventarios;
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

    @Column(name = "contacto")
    public String getContacto() {
        return this.contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @Column(name = "descripcion")
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "imagen")
    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
    public Set<Inventario> getInventarios() {
        return this.inventarios;
    }

    public void setInventarios(Set<Inventario> inventarios) {
        this.inventarios = inventarios;
    }
}
