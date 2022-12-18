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
	private TextField txtNombre;

	@FXML
	private ChoiceBox chbEspecialidad;

	@FXML
	private TextField txtApellido;

	@FXML
	private TextField txtEdad;
	@FXML
	private Button btnBorrar;
	@FXML
	private Button btnAnadir;
	@FXML
	private TableView tableAlumnos;
	@FXML
	private TableColumn<Alumno, String> tbApellido;
	@FXML
	private TableColumn<Alumno, Integer> tbEdad;
	@FXML
	private TableColumn<Alumno, String> tbNombre;
	@FXML
	private TableColumn<Alumno, String> tbEspecialidad;

	public ObservableList<String> listaEspecialidades = FXCollections
			.observableArrayList("DAM", "DAW", "FullStack",
			"ASIR");

	private ObservableList<Alumno> listaAlumnos = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		chbEspecialidad.setItems(listaEspecialidades);
		tbApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
		tbEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		tbNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tbEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));

		ObservableList listaAlumnosBD = getAlumnosBD();
		tableAlumnos.setItems(listaAlumnosBD);
	}

	private ObservableList<Alumno> getAlumnosBD() {

		/*
		 * Creamos la ObservableList donde almacenaremos los alumnos obtenidos de la Base
		 * de datos.
		 */
		ObservableList<Alumno> listaAlumnosBD = FXCollections.observableArrayList();

		// Nos conectamos a la BD

		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		String query = "select * from alumnosDI";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Alumno alumno = new Alumno(
						rs.getInt("id"),
						rs.getString("nombre"), 
						rs.getString("apellido"),
						rs.getString("especialidad"),
						rs.getInt("edad"));

				listaAlumnosBD.add(alumno);
			}
			// Cerramos la conexión.
			connection.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return listaAlumnosBD;
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
	public void anadirAlumno(ActionEvent event) {

		if (txtEdad.getText().isEmpty() || txtNombre.getText().isEmpty()
				|| chbEspecialidad.getSelectionModel().isEmpty() || txtApellido.getText().isBlank()) {
			Alert alertaEmpty = new Alert(AlertType.ERROR);
			alertaEmpty.setTitle("Debes rellenar todos los campos");
			alertaEmpty.setHeaderText("Hay campos vacios");
			alertaEmpty.setContentText("Por favor, rellene todos los campos");
			alertaEmpty.showAndWait();
		} else {
			if (esNumero(txtEdad.getText())) {
				Alumno a = new Alumno(txtNombre.getText(),  txtApellido.getText(),chbEspecialidad.getValue().toString(),
						Integer.parseInt(txtEdad.getText()));

				// listaLibros.add(l);
				// tableLibros.setItems(listaLibros);

				// DEJAMOS LOS CAMPOS EN BLANCO PARA SEGUIR INTRODUCIENDO LIBROS.
				txtNombre.clear();
				chbEspecialidad.getSelectionModel().clearSelection();
				txtApellido.clear();
				txtEdad.clear();

				// Nos conectamos a la base de datos
				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();

				try {
					String query = "insert into alumnosDI(nombre,apellido,especialidad,edad)" + "VALUES(?,?,?,?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, a.getNombre());
					ps.setString(2, a.getApellido());
					ps.setString(3, a.getEspecialidad());
					ps.setInt(4, a.getEdad());
					ps.executeUpdate();

					// CERRAMOS SESIÓN
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// Después de insertar actualizamos la tabla
				ObservableList listaAlumnosBD = getAlumnosBD();
				tableAlumnos.setItems(listaAlumnosBD);

			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No has introducido un número en el campo edad");
				alerta.setContentText("Por favor introduzca un número en el campo edad");
				alerta.showAndWait();
			}
		}

	}

	@FXML
	public void borrarAlumno(ActionEvent event) {
		System.out.println("Borrando un alumno");
		int indiceSeleccionado = tableAlumnos
				.getSelectionModel()
				.getSelectedIndex();

		System.out.println("Índice a borrar: " + indiceSeleccionado);

		if (indiceSeleccionado <= -1) {
			// ALERTA ERROR
			Alert alerta = new Alert(AlertType.ERROR);
			// Qué ha ocurrido?
			alerta.setTitle("Error al borrar");
			// Porqué ha ocurrido el error?
			alerta.setHeaderText("No se ha seleccionado ningún alumno a borrar");
			// Cómo solucionar el error.
			alerta.setContentText("Por favor, seleccione un alumno");
			// ShowAndWait para dejar bloqueada la pantalla hasta que cerremos la ventana de
			// error.
			alerta.showAndWait();
		} else {
			// tableAlumnos.getItems().remove(indiceSeleccionado);
			// tableAlumnos.getSelectionModel().clearSelection();
			DatabaseConnection dbConnection = new DatabaseConnection();
			Connection connection = dbConnection.getConnection();

			try {
				String query = "delete from alumnosDI where id=?";
				PreparedStatement ps = connection.prepareStatement(query);
				Alumno alumno = (Alumno) tableAlumnos.getSelectionModel().getSelectedItem();
				ps.setInt(1, alumno.getId());
				ps.executeUpdate();

				tableAlumnos.getSelectionModel().clearSelection();

				// Actualizamos la tabla
				ObservableList listaAlumnosBD = getAlumnosBD();
				tableAlumnos.setItems(listaAlumnosBD);
				// Cerramos conexión.
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
