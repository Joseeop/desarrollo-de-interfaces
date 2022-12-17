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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class indexVideojuegosController {

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtPrecio;
	@FXML
	private Button btnAnadir;
	@FXML
	private Button btnBorrar;
	@FXML
	private ChoiceBox chbPegi;

	@FXML
	private ChoiceBox chbConsola;

	@FXML
	private TableView tableVideojuegos;

	@FXML
	private TableColumn<Videojuego, String> clNombre;

	@FXML
	private TableColumn<Videojuego, Float> clPrecio;

	@FXML
	private TableColumn<Videojuego, String> clConsola;

	@FXML
	private TableColumn<Videojuego, Integer> clPegi;

	private ObservableList<Videojuego> listaTotal = FXCollections
			.observableArrayList();
	// Creamos las opciones que puede tener el ChoiceBox de consolas
	public ObservableList<String> listaConsolas = FXCollections.observableArrayList("PS5", "PC", "XBOX", "Nintendo");
	// Creamos las opciones que puede tener el ChoiceBox de PEGI
	public ObservableList<Integer> pegi = FXCollections.observableArrayList(3, 7, 12, 16, 18);

	@FXML
	private void initialize() {

		chbConsola.setItems(listaConsolas);
		chbPegi.setItems(pegi);

		clNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		clPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		clConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		clPegi.setCellValueFactory(new PropertyValueFactory<>("pegi"));

		ObservableList listaVideojuegosBD=getVideojuegosBD();
		tableVideojuegos.setItems(listaTotal);
	}
private ObservableList<Videojuego> getVideojuegosBD(){
		
		/*
		 * Creamos la ObservableList donde almacenaremos
		 * los libros obtediso de la Base de datos.
		 */
		ObservableList<Videojuego> listaVideojuegosBD =FXCollections.observableArrayList();
		
		
		// Nos conectamos a la BD
		 
		DatabaseConnection dbConnection=new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		String query = "select * from videojuegos";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Videojuego videojuego= new Videojuego(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getInt("precio"),
						rs.getString("consola"),
						rs.getInt("pegi")
						);
				
				listaVideojuegosBD.add(videojuego);
			}
			//Cerramos la conexión.
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		return listaVideojuegosBD;
	}

	@FXML
	private void anadirVideojuego(ActionEvent event) {

		if (txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty() || chbConsola.getSelectionModel().isEmpty()
				|| chbPegi.getSelectionModel().isEmpty()) {
			Alert alertaEmpty = new Alert(AlertType.ERROR);
			alertaEmpty.setTitle("Debes rellenar todos los campos");
			alertaEmpty.setHeaderText("Hay campos vacios");
			alertaEmpty.setContentText("Por favor, rellene todos los campos");
			alertaEmpty.showAndWait();
		} else {
			if (esNumero(txtPrecio.getText())) {
				Videojuego j = new Videojuego(txtNombre.getText(),Integer.parseInt(txtPrecio.getText()),
						chbConsola.getValue().toString(), Integer.parseInt(chbPegi.getValue().toString()));
				//listaTotal.add(j);

				txtNombre.clear();
				txtPrecio.clear();
				chbConsola.getSelectionModel().clearSelection();
				chbPegi.getSelectionModel().clearSelection();
				
				DatabaseConnection dbConnection=new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				
				
				
				try {
					String query = "insert into videojuegos(nombre,precio,consola,pegi)"
							+ "VALUES(?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, j.getNombre());
					ps.setInt(2, j.getPrecio());
					ps.setString(3, j.getConsola());
					ps.setInt(4, j.getPegi());
					ps.executeUpdate();
					
					//CERRAMOS SESIÓN
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Después de insertar actualizamos la tabla
				ObservableList listaVideojuegosBD=getVideojuegosBD();
				tableVideojuegos.setItems(listaVideojuegosBD);
				
				
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un precio válido");
				alerta.setContentText("Por favor introduzca un precio válido.");
				alerta.showAndWait();
			}
		}

	}

	public boolean esNumero(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	@FXML
	public void borrarVideojuego(ActionEvent event) {
		System.out.println("Borrando un libro");
		int indiceSeleccionado=tableVideojuegos
							.getSelectionModel()
							.getSelectedIndex();
		
		System.out.println("Índice a borrar: "+indiceSeleccionado);
		
		
		if(indiceSeleccionado <= -1) {
			//ALERTA ERROR
			Alert alerta = new Alert(AlertType.ERROR);
			//Qué ha ocurrido?
			alerta.setTitle("Error al borrar");
			//Porqué ha ocurrido el error?
			alerta.setHeaderText("No se ha seleccionado ningún videojuego a borrar");
			//Cómo solucionar el error.
			alerta.setContentText("Por favor, seleccione un videojuego");
			//ShowAndWait para dejar bloqueada la pantalla hasta que cerremos la ventana de error.
			alerta.showAndWait();
		}else {
		
			
			//Nos conectamos a la base de datos
			DatabaseConnection dbConnection=new DatabaseConnection();
			Connection connection = dbConnection.getConnection();
			
			
			try {
				String query = "delete from videojuegos where id=?";
				PreparedStatement ps= connection.prepareStatement(query);
				Videojuego videojuego= (Videojuego) tableVideojuegos.getSelectionModel().getSelectedItem();
				ps.setInt(1,videojuego.getId());
				ps.executeUpdate();
				
				tableVideojuegos.getSelectionModel().clearSelection();
				
				//Actualizamos la tabla
				ObservableList listaVideojuegosBD=getVideojuegosBD();
				tableVideojuegos.setItems(listaVideojuegosBD);
				//Cerramos conexión.
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
