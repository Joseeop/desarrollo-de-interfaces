package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class inicioSesionController {
	@FXML
	private TextField campoNombre;
	@FXML
	private Button botonEntrar;
	@FXML
	private Label lblMensaje;
	@FXML
	private PasswordField campoPass;
	
	@FXML
	public void mostrarMensaje(ActionEvent event) {
		lblMensaje.setText("Bienvenido, "+campoNombre.getText());
		campoNombre.setText(null);
		campoPass.setText(null);
	}
}
