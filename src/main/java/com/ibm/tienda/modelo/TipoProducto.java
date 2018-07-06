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
@Table(name = "tipo_producto", schema = "public")
public class TipoProducto implements java.io.Serializable {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String codigo;
    private String nombre;
    private Set<Producto> productos = new HashSet<Producto>(0);

    public TipoProducto() {
    }

    public TipoProducto(Long id, String codigo, String nombre,
        Set<Producto> productos) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.productos = productos;
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

    @Column(name = "codigo", nullable = false)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoProducto")
    public Set<Producto> getProductos() {
        return this.productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }
}
