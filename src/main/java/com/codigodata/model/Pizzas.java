package com.codigodata.model;

import javax.persistence.*;

@Entity
@Table(name = "pizzas")
public class Pizzas {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "sabor")
	private String sabor;

	@Column(name = "masa")
	private String masa;	

	@Column(name = "cantidad")
	private int cantidad;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}

	public String getMasa() {
		return masa;
	}

	public void setMasa(String masa) {
		this.masa = masa;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Pizzas [id=" + id + ", nombre=" + nombre + ", sabor=" + sabor + ", masa=" + masa + ", cantidad="
				+ cantidad + "]";
	}

	
}