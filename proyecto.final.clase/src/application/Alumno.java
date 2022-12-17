package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Alumno {

	//SOLO HEMOS MODIFICADO TITULO POR NOMBRE
	private int id;
	private SimpleStringProperty nombre;
	private SimpleStringProperty apellido;
	private SimpleStringProperty especialidad;
	private SimpleIntegerProperty edad;
	
	public Alumno(String nombre, String apellido, String especialidad, int edad) {
		
		this.nombre=new SimpleStringProperty(nombre);
		this.apellido =new SimpleStringProperty(apellido);
		this.especialidad =new SimpleStringProperty(especialidad);
		this.edad=new SimpleIntegerProperty(edad);
		
		
		
		
	}
	public Alumno(int id,String nombre, String apellido, String especialidad, int edad) {
		
		this.id=id;
		this.nombre=new SimpleStringProperty(nombre);
		this.apellido =new SimpleStringProperty(apellido);
		this.especialidad =new SimpleStringProperty(especialidad);
		this.edad=new SimpleIntegerProperty(edad);
		
		
		
		
		
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre.get();
	}
	public String getEspecialidad() {
		return especialidad.get();
	}
	public String getApellido() {
		return apellido.get();
	}
	public int getEdad() {
		return edad.get();
	}
	
	
	
	public void setNombre(String nombre) {
		this.nombre=new SimpleStringProperty(nombre);
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad=new SimpleStringProperty(especialidad);
	}
	public void setApellido(String apellido) {
		this.apellido=new SimpleStringProperty(apellido);
	}
	public void setEdad(int edad) {
		this.edad=new SimpleIntegerProperty(edad);
	}
	
}
