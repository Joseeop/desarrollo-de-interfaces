package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
	private TableView tableLibros;
	@FXML
	private TableColumn <Libro,String>tbAutor;
	@FXML
	private TableColumn <Libro,Integer>tbPaginas;
	@FXML
	private TableColumn <Libro,String>tbTitulo;
	@FXML
	private TableColumn <Libro,String>tbEditorial;
	
	public ObservableList<String> listaEditoriales= 
			FXCollections.observableArrayList("Altaya", "Planeta","CasiLeo","Kadokawa");
	
	
	
	private ObservableList<Libro> listaLibros= 
			FXCollections.observableArrayList(new Libro("La Biblia", "Planeta","CasiLeo",300));
	@FXML
	private void initialize() {
		chbEditorial.setItems(listaEditoriales);
		tbAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		tbPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));
		tbTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		tbEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
	}
}
