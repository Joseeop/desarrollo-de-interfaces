package application;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Videojuego {
	
	
	private SimpleStringProperty nombre;
	private SimpleFloatProperty precio;
	private SimpleStringProperty consola;
	private SimpleIntegerProperty pegi;
	
	public Videojuego(String nombre,float precio,String consola,int pegi) {
		
		this.nombre=new SimpleStringProperty (nombre);
		this.precio=new SimpleFloatProperty(precio);
		this.consola=new SimpleStringProperty(consola);
		this.pegi=new SimpleIntegerProperty(pegi);
	}

	public SimpleStringProperty getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		
		this.nombre=new SimpleStringProperty(nombre);
	}

	public SimpleFloatProperty getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = new SimpleFloatProperty(precio);
	}

	public SimpleStringProperty getConsola() {
		return consola;
	}

	public void setConsola(String consola) {
		this.consola = new SimpleStringProperty(consola);
	}

	public SimpleIntegerProperty getPegi() {
		return pegi;
	}

	public void setPegi(int pegi) {
		this.pegi = new SimpleIntegerProperty(pegi);
	}

}
