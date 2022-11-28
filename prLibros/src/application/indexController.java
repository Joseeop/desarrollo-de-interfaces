package application;

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
			.observableArrayList(new Libro("La Biblia", "Planeta", "CasiLeo", 300));

	@FXML
	private void initialize() {
		chbEditorial.setItems(listaEditoriales);
		tbAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		tbPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		tbTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		tbEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		tableLibros.setItems(listaLibros);
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
				listaLibros.add(l);

				// DEJAMOS LOS CAMPOS EN BLANCO PARA SEGUIR INTRODUCIENDO LIBROS.
				txtTitulo.clear();
				chbEditorial.getSelectionModel().clearSelection();
				txtAutor.clear();
				txtPaginas.clear();
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
		int indeceSeleccionado=tableLibros
							.getSelectionModel().getSelectedIndex();
		tableLibros.getItems().remove(indeceSeleccionado);
	}
}
