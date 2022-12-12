package application;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Libro {

	private int id;
	private SimpleStringProperty titulo;
	private SimpleStringProperty editorial;
	private SimpleStringProperty autor;
	private SimpleIntegerProperty paginas;
	
	public Libro(String titulo, String editorial, String autor, int paginas) {
		
		this.titulo=new SimpleStringProperty(titulo);
		this.editorial =new SimpleStringProperty(editorial);
		this.autor =new SimpleStringProperty(autor);
		this.paginas=new SimpleIntegerProperty(paginas);
		
		
		
		
	}
public Libro(int id,String titulo, String editorial, String autor, int paginas) {
		
		this.id=id;
		this.titulo=new SimpleStringProperty(titulo);
		this.editorial =new SimpleStringProperty(editorial);
		this.autor =new SimpleStringProperty(autor);
		this.paginas=new SimpleIntegerProperty(paginas);
		
		
		
		
		
	}
	public String getTitulo() {
		return titulo.get();
	}
	public String getAutor() {
		return autor.get();
	}
	public String getEditorial() {
		return editorial.get();
	}
	public int getPaginas() {
		return paginas.get();
		//Los get son para los property
	}
	public int getId() {
		return id;
	}
	
	
	public void setTitulo(String titulo) {
		this.titulo=new SimpleStringProperty(titulo);
	}
	public void setAutor(String autor) {
		this.autor=new SimpleStringProperty(autor);
	}
	public void setEditorial(String editorial) {
		this.editorial=new SimpleStringProperty(editorial);
	}
	public void setPaginas(int paginas) {
		this.paginas=new SimpleIntegerProperty(paginas);
	}
	
}
