package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Button btn = new Button("Click bait");
			btn.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent event) {
					System.out.println("Hola Mundo");
				}
			});
			Label lbl = new Label("Hola mundo");
			
			
//			StackPane panel = new StackPane();
			
//			panel.setAlignment(lbl,Pos.TOP_CENTER);
//			panel.setAlignment(btn,Pos.CENTER);
//			panel.getChildren().addAll(lbl,btn);
			GridPane panel = new GridPane();
			
			Button btn1=new Button("Botón1");
			Button btn2=new Button("Botón2");
			Button btn3=new Button("Botón3");
			Button btn4=new Button("Botón4");
			
			panel.setVgap(15);
			panel.setHgap(15);
			panel.setPadding(new Insets(15));
			
			panel.add(btn1, 0, 0);
			panel.add(btn4, 0, 1);
			panel.add(btn2, 1, 0);
			panel.add(btn3, 1, 1);
			
//			VBox panelVertival=new VBox(15);
//			panelVertival.setPadding(new Insets(15));
//			panelVertival.getChildren().addAll(btn1,btn2,btn3);
			
			//BorderPane panel= new BorderPane();
			//panel.setRight(panelVertival);
			
			Scene scene = new Scene(panel,400,300);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Introducción a JavaFX");
			primaryStage.show();
			primaryStage.getIcons().add( new Image("/application/desafios.png"));
			
			//HBox panel2= new HBox(105);
//			panel2.setPadding(new Insets(5));
//			panel2.getChildren().addAll(btn1,btn2,btn3);
//			Scene scene = new Scene(panel,400,300);
//			primaryStage.setScene(scene);
//			primaryStage.setTitle("Introducción a JavaFX");
//			primaryStage.show();
//			primaryStage.getIcons().add( new Image("/application/desafios.png"));

			
			} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
