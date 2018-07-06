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
public class DetalleInventarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DetalleInventarioDTO.class);
    private Integer cantidad;
    private Long id;
    private Double precioBase;
    private Long id_Inventario;
    private Long id_Producto;
    private String nombrePoducto;

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public Long getId_Inventario() {
        return id_Inventario;
    }

    public void setId_Inventario(Long id_Inventario) {
        this.id_Inventario = id_Inventario;
    }

    public Long getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(Long id_Producto) {
        this.id_Producto = id_Producto;
    }

	public String getNombrePoducto() {
		return nombrePoducto;
	}

	public void setNombrePoducto(String nombrePoducto) {
		this.nombrePoducto = nombrePoducto;
	}
    
    
}
