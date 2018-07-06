package com.ibm.tienda.modelo.dto;

import java.util.Date;
import java.util.List;

public class RegistrarPedidoDTO {
	
	private String nombre;
	private String apellido;
	private Long idTipoId;
	private String identificacion;
	private String direccion;
	
	private Date fechaPedido;
	
	private List<DetallePedidoDTO> detallePedidoDTOs;

	
	public RegistrarPedidoDTO() {
	}
	
	public RegistrarPedidoDTO(String nombre, String apellido, Long idTipoId, String identificacion, String direccion,
			Date fechaPedido, List<DetallePedidoDTO> detallePedidoDTOs) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.idTipoId = idTipoId;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.fechaPedido = fechaPedido;
		this.detallePedidoDTOs = detallePedidoDTOs;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getIdTipoId() {
		return idTipoId;
	}

	public void setIdTipoId(Long idTipoId) {
		this.idTipoId = idTipoId;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public List<DetallePedidoDTO> getDetallePedidoDTOs() {
		return detallePedidoDTOs;
	}

	public void setDetallePedidoDTOs(List<DetallePedidoDTO> detallePedidoDTOs) {
		this.detallePedidoDTOs = detallePedidoDTOs;
	}
	
	
	
}
