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
@Table(name = "usuario", schema = "public")
public class Usuario implements java.io.Serializable {
   
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    @NotNull
    private TipoIdentificacion tipoIdentificacion;
    private String apellido;
    private String contrasena;
    private String email;
    private Date fechaNacimiento;
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String identificacion;
    private String nombre;
    private Set<Pedido> pedidos = new HashSet<Pedido>(0);

    public Usuario() {
    }

    public Usuario(Long id, String apellido, String contrasena, String email,
        Date fechaNacimiento, String identificacion, String nombre,
        Set<Pedido> pedidos, TipoIdentificacion tipoIdentificacion) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.apellido = apellido;
        this.contrasena = contrasena;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.pedidos = pedidos;
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
    @JoinColumn(name = "tipo_identificacion_id")
    public TipoIdentificacion getTipoIdentificacion() {
        return this.tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    @Column(name = "apellido")
    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Column(name = "contrasena")
    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Column(name = "email")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "fecha_nacimiento")
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Column(name = "identificacion", nullable = false)
    public String getIdentificacion() {
        return this.identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    public Set<Pedido> getPedidos() {
        return this.pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
