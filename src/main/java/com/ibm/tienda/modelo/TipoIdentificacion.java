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
@Table(name = "tipo_identificacion", schema = "public")
public class TipoIdentificacion implements java.io.Serializable {
    private Long id;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String codigo;
    private String nombre;
    private Set<Usuario> usuarios = new HashSet<Usuario>(0);

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(Long id, String codigo, String nombre,
        Set<Usuario> usuarios) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.usuarios = usuarios;
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoIdentificacion")
    public Set<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
