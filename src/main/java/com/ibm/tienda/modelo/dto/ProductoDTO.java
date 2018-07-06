package com.ibm.tienda.modelo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.util.Date;


/**
* @author Zathura Code Generator http://zathuracode.org
* www.zathuracode.org
*
*/
public class ProductoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProductoDTO.class);
    private String codigo;
    private Long id;
    private String nombre;
    private Long id_TipoProducto;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId_TipoProducto() {
        return id_TipoProducto;
    }

    public void setId_TipoProducto(Long id_TipoProducto) {
        this.id_TipoProducto = id_TipoProducto;
    }
}
