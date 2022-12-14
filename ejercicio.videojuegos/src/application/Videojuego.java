package application;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Videojuego {
	
	private int id;
	private SimpleStringProperty nombre;
	private SimpleIntegerProperty precio;
	private SimpleStringProperty consola;
	private SimpleIntegerProperty pegi;
	
	public Videojuego(String nombre,int precio,String consola,int pegi) {
		
		this.nombre=new SimpleStringProperty (nombre);
		this.precio=new SimpleIntegerProperty(precio);
		this.consola=new SimpleStringProperty(consola);
		this.pegi=new SimpleIntegerProperty(pegi);
	}
public Videojuego(int id,String nombre,int precio,String consola,int pegi) {
		
		this.id=id;
		this.nombre=new SimpleStringProperty (nombre);
		this.precio=new SimpleIntegerProperty(precio);
		this.consola=new SimpleStringProperty(consola);
		this.pegi=new SimpleIntegerProperty(pegi);
	}

	public String getNombre() {
		return nombre.get();
	}
	public int getId() {
		return id;
	}

	public void setNombre(String nombre) {
		
		this.nombre=new SimpleStringProperty(nombre);
	}

	public int getPrecio() {
		return precio.get();
	}

	public void setPrecio(int precio) {
		this.precio = new SimpleIntegerProperty(precio);
	}

	public String getConsola() {
		return consola.get();
	}

	public void setConsola(String consola) {
		this.consola = new SimpleStringProperty(consola);
	}

	public int getPegi() {
		return pegi.get();
	}

	public void setPegi(int pegi) {
		this.pegi = new SimpleIntegerProperty(pegi);
	}
//	public void setTitulo(String titulo) {
//		this.titulo=new SimpleStringProperty(titulo);
//	}
//	public void setAutor(String autor) {
//		this.autor=new SimpleStringProperty(autor);
//	}
//	public void setEditorial(String editorial) {
//		this.editorial=new SimpleStringProperty(editorial);
//	}
//	public void setPaginas(int paginas) {
//		this.paginas=new SimpleIntegerProperty(paginas);
//	}
}
