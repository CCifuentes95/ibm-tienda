package com.ibm.tienda.modelo.dto;

public class ResponseDTO {
	private String error;
	private String mensaje;
	
	public ResponseDTO() {
		super();
	}
	
	public ResponseDTO(String error, String mensaje) {
		super();
		this.error = error;
		this.mensaje = mensaje;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
	
}
