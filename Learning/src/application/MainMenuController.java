package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainMenuController implements Initializable  {
	
	@FXML
	ImageView pantryImage;
	
	@FXML
	ImageView recipeImage;
	
	@FXML
	ImageView plannerImage;
	
	@FXML
	Button pantryBtn;
	
	@FXML
	Button recipeBtn;
	
	@FXML
	Button plannerBtn;
	
	@FXML
	public void showPantryView(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("PantryView.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void showRecipeView(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Recipes.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void showPlannerView(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Planner.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
