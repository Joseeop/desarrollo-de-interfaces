package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ejercicio3 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			
			BorderPane panel=new BorderPane();
			Label lbl_numero=new Label("Número marcado: ");
			panel.setBottom(lbl_numero);
			
			GridPane grid = new GridPane();
			panel.setCenter(grid);
			
			Button b7 = new Button("7");
			b7.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					String numero= lbl_numero.getText() + "7";
					lbl_numero.setText(numero);
				}
			});
			Button b8 = new Button("8");
			b8.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					String numero= lbl_numero.getText() + "8";
					lbl_numero.setText(numero);
				}
			});
			Button b9 = new Button("9");
			
			Button b4 = new Button("4");
			Button b5 = new Button("5");
			Button b6 = new Button("6");
			Button bLlamar = new Button("Llamar");
			
			
			Button b1 = new Button("1");
			Button b2= new Button("2");
			Button b3 = new Button("3");
			Button bColgar=new Button("Colgar");
			
			Button b0 = new Button("0");
			
			grid.add(b7, 0,0);
			grid.add(b8, 1,0);
			grid.add(b9, 2,0);
			
			grid.add(b4, 0,1);
			grid.add(b5, 1,1);
			grid.add(b6, 2,1);
			grid.add(bLlamar, 3,1);
			
			grid.add(b1, 0,2);
			grid.add(b2, 1,2);
			grid.add(b3, 2,2);
			grid.add(bColgar, 3,2);
			
			
			grid.add(b0, 1,3);
			
			
			Scene scene = new Scene(panel,200,200);
			//scene.getStylesheets().add(getClass().getResource("application.css"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
