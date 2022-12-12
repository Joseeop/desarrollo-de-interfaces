package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class indexController {

	@FXML
	private TextField txtTitulo;

	@FXML
	private ChoiceBox chbEditorial;

	@FXML
	private TextField txtAutor;
	
	@FXML
	private TextField txtPaginas;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btnAnadir;
	@FXML
	private TableView tableLibros;
	@FXML
	private TableColumn<Libro, String> tbAutor;
	@FXML
	private TableColumn<Libro, Integer> tbPaginas;
	@FXML
	private TableColumn<Libro, String> tbTitulo;
	@FXML
	private TableColumn<Libro, String> tbEditorial;

	public ObservableList<String> listaEditoriales = FXCollections.observableArrayList("Altaya", "Planeta", "CasiLeo",
			"Kadokawa");

	private ObservableList<Libro> listaLibros = FXCollections
			.observableArrayList();

	@FXML
	private void initialize() {
		chbEditorial.setItems(listaEditoriales);
		tbAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		tbPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		tbTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		tbEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		
		ObservableList listaLibrosBD=getLibrosBD();
		tableLibros.setItems(listaLibrosBD);
	}
	
	private ObservableList<Libro> getLibrosBD(){
		
		/*
		 * Creamos la ObservableList donde almacenaremos
		 * los libros obtediso de la Base de datos.
		 */
		ObservableList<Libro> listaLibrosBD =FXCollections.observableArrayList();
		
		
		// Nos conectamos a la BD
		 
		DatabaseConnection dbConnection=new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		String query = "select * from libros";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Libro libro= new Libro(
						rs.getInt("id"),
						rs.getString("titulo"),
						rs.getString("editorial"),
						rs.getString("autor"),
						rs.getInt("paginas")
						);
				
				listaLibrosBD.add(libro);
			}
			//Cerramos la conexión.
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		return listaLibrosBD;
	}

	// FUNCION PARA COMPROBAR SI ES UN NÚMERO
	public boolean esNumero(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@FXML
	public void anadirLibro(ActionEvent event) {

		if (txtPaginas.getText().isEmpty() || txtTitulo.getText().isEmpty()
				|| chbEditorial.getSelectionModel().isEmpty() || txtAutor.getText().isBlank()) {
			Alert alertaEmpty = new Alert(AlertType.ERROR);
			alertaEmpty.setTitle("Debes rellenar todos los campos");
			alertaEmpty.setHeaderText("Hay campos vacios");
			alertaEmpty.setContentText("Por favor, rellene todos los campos");
			alertaEmpty.showAndWait();
		} else {
			if (esNumero(txtPaginas.getText())) {
				Libro l = new Libro(txtTitulo.getText(), chbEditorial.getValue().toString(), txtAutor.getText(),
						Integer.parseInt(txtPaginas.getText()));
				
				
				//listaLibros.add(l);
				//tableLibros.setItems(listaLibros);

				// DEJAMOS LOS CAMPOS EN BLANCO PARA SEGUIR INTRODUCIENDO LIBROS.
				txtTitulo.clear();
				chbEditorial.getSelectionModel().clearSelection();
				txtAutor.clear();
				txtPaginas.clear();
				
				//Nos conectamos a la base de datos
				DatabaseConnection dbConnection=new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				
				
				
				try {
					String query = "insert into libros(titulo,editorial,autor,paginas)"
							+ "VALUES(?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, l.getTitulo());
					ps.setString(2, l.getEditorial());
					ps.setString(3, l.getAutor());
					ps.setInt(4, l.getPaginas());
					ps.executeUpdate();
					
					//CERRAMOS SESIÓN
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Después de insertar actualizamos la tabla
				ObservableList listaLibrosBD=getLibrosBD();
				tableLibros.setItems(listaLibrosBD);
				
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un número en las páginas");
				alerta.setContentText("Por favor introduzca un número en la páginas");
				alerta.showAndWait();
			}
		}

	}

	@FXML
	public void borrarLibro(ActionEvent event) {
		System.out.println("Borrando un libro");
		int indiceSeleccionado=tableLibros
							.getSelectionModel()
							.getSelectedIndex();
		
		System.out.println("Índice a borrar: "+indiceSeleccionado);
		
		
		if(indiceSeleccionado <= -1) {
			//ALERTA ERROR
			Alert alerta = new Alert(AlertType.ERROR);
			//Qué ha ocurrido?
			alerta.setTitle("Error al borrar");
			//Porqué ha ocurrido el error?
			alerta.setHeaderText("No se ha seleccionado ningún libro a borrar");
			//Cómo solucionar el error.
			alerta.setContentText("Por favor, seleccione un libro");
			//ShowAndWait para dejar bloqueada la pantalla hasta que cerremos la ventana de error.
			alerta.showAndWait();
		}else {
			//tableLibros.getItems().remove(indiceSeleccionado);
			//tableLibros.getSelectionModel().clearSelection();
			
			//Nos conectamos a la base de datos
			DatabaseConnection dbConnection=new DatabaseConnection();
			Connection connection = dbConnection.getConnection();
			
			
			try {
				String query = "delete from libros where id=?";
				PreparedStatement ps= connection.prepareStatement(query);
				Libro libro= (Libro) tableLibros.getSelectionModel().getSelectedItem();
				ps.setInt(1,libro.getId());
				ps.executeUpdate();
				
				tableLibros.getSelectionModel().clearSelection();
				
				//Actualizamos la tabla
				ObservableList listaLibrosBD=getLibrosBD();
				tableLibros.setItems(listaLibrosBD);
				//Cerramos conexión.
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
