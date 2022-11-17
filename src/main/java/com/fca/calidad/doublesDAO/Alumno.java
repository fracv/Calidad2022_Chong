package com.fca.calidad.doublesDAO;

public class Alumno {

	private String nombre;
	private String id;
	private int edad;
	private String correo;
	
	public Alumno(String nombre, String id, int edad, String correo) {
		super();
		this.nombre = nombre;
		this.id = id;
		this.edad = edad;
		this.correo = correo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
}
