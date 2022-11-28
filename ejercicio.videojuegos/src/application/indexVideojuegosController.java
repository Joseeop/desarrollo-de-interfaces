package application;

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
			.observableArrayList(new Videojuego("CS:GO", 10, "PC", 3));
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

		tableVideojuegos.setItems(listaTotal);
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
				Videojuego j = new Videojuego(txtNombre.getText(), Float.parseFloat(txtPrecio.getText()),
						chbConsola.getValue().toString(), Integer.parseInt(chbPegi.getValue().toString()));
				listaTotal.add(j);

				txtNombre.clear();
				txtPrecio.clear();
				chbConsola.getSelectionModel().clearSelection();
				chbPegi.getSelectionModel().clearSelection();
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

}
