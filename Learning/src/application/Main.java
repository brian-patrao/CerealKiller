package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	Stage window;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setTitle("Recipe App");
		
		try {
			Parent root = FXMLLoader.load(getClass().getResource("WelcomePage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			window.setScene(scene);
			window.show();	
			
		} catch (Exception e) {
			System.out.println("Inside catch" + e.getMessage());
			e.printStackTrace();
		}
	}
}
