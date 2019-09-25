package com.everis.springcore.model;

/**
 * Javabean, POJO - plain object java - objeto plano, VO value object
 * @author Dell5458
 *
 */

public class Cliente {
	private int idCliente;
	private String nombre;
	private String apaterno;
	private String amaterno;
	private String rfc;
	
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apaterno=" + apaterno + ", amaterno="
				+ amaterno + ", rfc=" + rfc + "]";
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApaterno() {
		return apaterno;
	}
	public void setApaterno(String apaaterno) {
		this.apaterno = apaaterno;
	}
	public String getAmaterno() {
		return amaterno;
	}
	public void setAmaterno(String amaterno) {
		this.amaterno = amaterno;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
	
}
