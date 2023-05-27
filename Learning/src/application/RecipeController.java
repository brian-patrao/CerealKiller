package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;


 

public class RecipeController implements Initializable {
	
	String recipeindex;
	    
    @FXML
    private Button idSearchButton;
    
    @FXML
    private TextField idSearchText;
    
    @FXML
    private ListView<String> idlistview;
    
    @FXML
    private Text idrecipetitle;
    
    @FXML
    private Text idrecipeinstructiontitle;
    
    @FXML
    private ImageView idrecipeimage;
    
    @FXML
    private TextArea idrecipeinstructions;
    
    @FXML
    private Text idrecipecategory;
    
    @FXML
    private TableColumn<Ingredients, String> idrecipeingredients;
    
    @FXML
    private TableColumn<Ingredients, String> idrecipequantity;
    
    @FXML
    private TableView<Ingredients> idrecipetable;
    
    @FXML
    private MediaView idrecipemedia;
    
    @FXML
    private WebView idrecipewebview;
    
    @FXML
    ComboBox<String> recipeComboBox;
    
    @FXML
    private void goToMainMenu(MouseEvent event) throws IOException {
    	Scene2Controller sc = new Scene2Controller();
    	sc.switchToScene1(event);
    }
    
    @FXML
    private void addToFavourites(ActionEvent event) {
    	Scene2Controller sc = new Scene2Controller();
    	String query = "INSERT INTO favourites (mealname) VALUES (" + "'" + idrecipetitle.getText() + "')";
		sc.executeQuery(query);
    }
    
    
    @FXML
    private void SearchButtonClick() {
    	System.out.println("Recipe box items: "+ recipeComboBox.getValue());
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://www.themealdb.com/api/json/v2/9973533/filter.php?i=".concat(recipeComboBox.getValue().toString())))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("The response is -------"+response);
		
		final JSONObject obj = new JSONObject(response.body());
	    final JSONArray meals = obj.getJSONArray("meals");
	    Meal[] mealArr = new Meal[meals.length()];
	    JSONObject[] arr = new JSONObject[meals.length()];
	    for(int i=0;i<meals.length();i++) {
	    	arr[i] = (JSONObject) meals.get(i);
	    	mealArr[i] = new Meal(arr[i].getInt("idMeal"),arr[i].getString("strMeal"),arr[i].getString("strMealThumb"));
	    }
	    
	    idlistview.getItems().clear();
	    ArrayList<String> lv = new ArrayList<String>();
	    for(int i=0;i<meals.length();i++) {
	    	arr[i] = (JSONObject) meals.get(i);
	    	lv.add(arr[i].getString("strMeal"));
	    }
	    for(String lvs : lv) {
	    	idlistview.getItems().add(lvs);
	    }
	    
//	    ListView<String> listView =new ListView<String>();
	    idlistview.getSelectionModel().select(0);	    
	    
	    
	    idlistview.getSelectionModel().selectedItemProperty().addListener((ChangeListener<? super String>) new ChangeListener<String>() {
	        @Override
	        public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	        	String myString = newValue;
	        	System.out.println("New Valye is "+ newValue);
	        	myString = myString.replaceAll(" ", "_");
	        	HttpRequest request2 = HttpRequest.newBuilder()
	    				.uri(URI.create("https://www.themealdb.com/api/json/v2/9973533/search.php?s=".concat(myString)))
	    				.method("GET", HttpRequest.BodyPublishers.noBody())
	    				.build();
	    		HttpResponse<String> response2 = null;
	    		try {
	    			response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
	    		} catch (IOException e) {
	    			e.printStackTrace();
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    		}
	    	
	    		final JSONObject obj2 = new JSONObject(response2.body());
	    	    final JSONArray recipes = obj2.getJSONArray("meals");
	    	    Recipe[] recipeArr = new Recipe[recipes.length()];
	    	    JSONObject[] arr2 = new JSONObject[recipes.length()];
	    	    ObservableList<Ingredients> ingredients = FXCollections.observableArrayList();
	    	    for(int i=0;i<recipes.length();i++) {
	    	    	arr2[i] = (JSONObject) recipes.get(i);
	    	    	
	    	    	recipeArr[i] = new Recipe(arr2[i].getInt("idMeal"),arr2[i].getString("strMeal"),arr2[i].getString("strCategory"),
	    	    			arr2[i].getString("strInstructions"), arr2[i].getString("strMealThumb"), arr2[i].getString("strYoutube"),
	    	    			arr2[i].getString("strIngredient1"), arr2[i].getString("strIngredient2"), arr2[i].getString("strIngredient3"),
	    	    			arr2[i].getString("strIngredient4"), arr2[i].getString("strIngredient5"), arr2[i].getString("strIngredient6"),
	    	    			arr2[i].getString("strIngredient7"), arr2[i].getString("strIngredient8"), arr2[i].getString("strIngredient9"),
	    	    			arr2[i].getString("strIngredient10"), arr2[i].getString("strIngredient11"), arr2[i].getString("strIngredient12"),
	    	    			arr2[i].getString("strIngredient13"), arr2[i].getString("strIngredient14"), arr2[i].getString("strIngredient15"),
	    	    			arr2[i].getString("strIngredient16"), arr2[i].getString("strIngredient17"), arr2[i].getString("strIngredient18"),
	    	    			arr2[i].getString("strIngredient19"), arr2[i].getString("strIngredient20"), arr2[i].getString("strMeasure1"), 
	    	    			arr2[i].getString("strMeasure2"), arr2[i].getString("strMeasure3"), arr2[i].getString("strMeasure4"),
	    	    			arr2[i].getString("strMeasure5"), arr2[i].getString("strMeasure6"), arr2[i].getString("strMeasure7"), arr2[i].getString("strMeasure8"),
	    	    			arr2[i].getString("strMeasure9"), arr2[i].getString("strMeasure10"), arr2[i].getString("strMeasure11"), arr2[i].getString("strMeasure12"),
	    	    			arr2[i].getString("strMeasure13"), arr2[i].getString("strMeasure14"), arr2[i].getString("strMeasure15"), arr2[i].getString("strMeasure16"),
	    	    			arr2[i].getString("strMeasure17"), arr2[i].getString("strMeasure18"), arr2[i].getString("strMeasure19"), arr2[i].getString("strMeasure20"));
	    	    	Ingredients ingr = new Ingredients(arr2[i].getString("strIngredient1"),arr2[i].getString("strMeasure1"));
	    	    }
	    	    
	    	    idrecipetitle.setText(arr2[0].getString("strMeal"));
	    	    Image img3 = new Image(arr2[0].getString("strMealThumb"));
	            idrecipeimage.setImage(img3);
	            idrecipecategory.setText(arr2[0].getString("strCategory"));
	            idrecipeinstructiontitle.setText("Recipe Instructions:");
	            idrecipeinstructions.setText(arr2[0].getString("strInstructions"));
	            
	            WebEngine webEngine = idrecipewebview.getEngine();
	            String test = arr2[0].getString("strYoutube");
	            test = test.replaceAll(".+=", "");
	            webEngine.load("http://www.youtube.com/watch?v=".concat(test));
	            idrecipewebview.setPrefSize(640, 390);
	            
	            
	            for(int i = 0; i<recipes.length(); i++) {
	            	Ingredients ingr1;
	            	ingr1 = new Ingredients(recipeArr[0].getRecipeIng1(),recipeArr[0].getRecipeQty1());
	            	ingredients.add(ingr1);
	            	Ingredients ingr2;
	            	ingr2 = new Ingredients(recipeArr[0].getRecipeIng2(),recipeArr[0].getRecipeQty2());
	            	ingredients.add(ingr2);
	            	Ingredients ingr3;
	            	ingr3 = new Ingredients(recipeArr[i].getRecipeIng3(),recipeArr[i].getRecipeQty3());
	            	ingredients.add(ingr3);
	            	Ingredients ingr4;
	            	ingr4 = new Ingredients(recipeArr[i].getRecipeIng4(),recipeArr[i].getRecipeQty4());
	            	ingredients.add(ingr4);
	            	Ingredients ingr5;
	            	ingr5 = new Ingredients(recipeArr[i].getRecipeIng5(),recipeArr[i].getRecipeQty5());
	            	ingredients.add(ingr5);
	            	Ingredients ingr6;
	            	ingr6 = new Ingredients(recipeArr[i].getRecipeIng6(),recipeArr[i].getRecipeQty6());
	            	ingredients.add(ingr6);
	            	Ingredients ingr7;
	            	ingr7 = new Ingredients(recipeArr[i].getRecipeIng7(),recipeArr[i].getRecipeQty7());
	            	ingredients.add(ingr7);
	            	Ingredients ingr8;
	            	ingr8 = new Ingredients(recipeArr[i].getRecipeIng8(),recipeArr[i].getRecipeQty8());
	            	ingredients.add(ingr8);
	            	Ingredients ingr9;
	            	ingr9 = new Ingredients(recipeArr[i].getRecipeIng9(),recipeArr[i].getRecipeQty9());
	            	ingredients.add(ingr9);
	            	Ingredients ingr10;
	            	ingr10 = new Ingredients(recipeArr[i].getRecipeIng10(),recipeArr[i].getRecipeQty10());
	            	ingredients.add(ingr10);
	            	Ingredients ingr11;
	            	ingr11 = new Ingredients(recipeArr[i].getRecipeIng11(),recipeArr[i].getRecipeQty11());
	            	ingredients.add(ingr11);
	            	Ingredients ingr12;
	            	ingr12 = new Ingredients(recipeArr[i].getRecipeIng12(),recipeArr[i].getRecipeQty12());
	            	ingredients.add(ingr12);
	            	Ingredients ingr13;
	            	ingr13 = new Ingredients(recipeArr[i].getRecipeIng13(),recipeArr[i].getRecipeQty13());
	            	ingredients.add(ingr13);
	            	Ingredients ingr14;
	            	ingr14 = new Ingredients(recipeArr[i].getRecipeIng14(),recipeArr[i].getRecipeQty14());
	            	ingredients.add(ingr14);
	            	Ingredients ingr15;
	            	ingr15 = new Ingredients(recipeArr[i].getRecipeIng15(),recipeArr[i].getRecipeQty15());
	            	ingredients.add(ingr15);
	            	Ingredients ingr16;
	            	ingr16 = new Ingredients(recipeArr[i].getRecipeIng16(),recipeArr[i].getRecipeQty16());
	            	ingredients.add(ingr16);
	            	Ingredients ingr17;
	            	ingr17 = new Ingredients(recipeArr[i].getRecipeIng17(),recipeArr[i].getRecipeQty17());
	            	ingredients.add(ingr17);
	            	Ingredients ingr18;
	            	ingr18 = new Ingredients(recipeArr[i].getRecipeIng18(),recipeArr[i].getRecipeQty18());
	            	ingredients.add(ingr18);
	            	Ingredients ingr19;
	            	ingr19 = new Ingredients(recipeArr[i].getRecipeIng19(),recipeArr[i].getRecipeQty19());
	            	ingredients.add(ingr19);
	            	Ingredients ingr20;
	            	ingr20 = new Ingredients(recipeArr[i].getRecipeIng20(),recipeArr[i].getRecipeQty20());
	            	ingredients.add(ingr20);
	            }
	            idrecipeingredients.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("recipeIngredient"));
	            idrecipequantity.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("recipeQuantity"));
	            idrecipetable.setItems(ingredients);
	        }
	    });
	    
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//leftPane
		Scene2Controller sc = new Scene2Controller();
    	ObservableList<Groceries> groceryList =sc.getGroceriesList();
    	
    	for(Groceries groceryItem: groceryList )
    		recipeComboBox.getItems().add(groceryItem.getGroceryName().toLowerCase());
    	
    	recipeComboBox.setPromptText("Search your choice");

		//rightPane
		HttpRequest request2 = HttpRequest.newBuilder()
				.uri(URI.create("https://www.themealdb.com/api/json/v2/9973533/search.php?s=Apam_balik"))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response2 = null;
		try {
			response2 = HttpClient.newHttpClient().send(request2, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		final JSONObject obj2 = new JSONObject(response2.body());
	    final JSONArray recipes = obj2.getJSONArray("meals");
	    Recipe[] recipeArr = new Recipe[recipes.length()];
	    JSONObject[] arr2 = new JSONObject[recipes.length()];
	    ObservableList<Ingredients> ingredients = FXCollections.observableArrayList();
	    for(int i=0;i<recipes.length();i++) {
	    	arr2[i] = (JSONObject) recipes.get(i);
	    	recipeArr[i] = new Recipe(arr2[i].getInt("idMeal"),arr2[i].getString("strMeal"),arr2[i].getString("strCategory"),
	    			arr2[i].getString("strInstructions"), arr2[i].getString("strMealThumb"), arr2[i].getString("strYoutube"),
	    			arr2[i].getString("strIngredient1"), arr2[i].getString("strIngredient2"), arr2[i].getString("strIngredient3"),
	    			arr2[i].getString("strIngredient4"), arr2[i].getString("strIngredient5"), arr2[i].getString("strIngredient6"),
	    			arr2[i].getString("strIngredient7"), arr2[i].getString("strIngredient8"), arr2[i].getString("strIngredient9"),
	    			arr2[i].getString("strIngredient10"), arr2[i].getString("strIngredient11"), arr2[i].getString("strIngredient12"),
	    			arr2[i].getString("strIngredient13"), arr2[i].getString("strIngredient14"), arr2[i].getString("strIngredient15"),
	    			arr2[i].getString("strIngredient16"), arr2[i].getString("strIngredient17"), arr2[i].getString("strIngredient18"),
	    			arr2[i].getString("strIngredient19"), arr2[i].getString("strIngredient20"), arr2[i].getString("strMeasure1"), 
	    			arr2[i].getString("strMeasure2"), arr2[i].getString("strMeasure3"), arr2[i].getString("strMeasure4"),
	    			arr2[i].getString("strMeasure5"), arr2[i].getString("strMeasure6"), arr2[i].getString("strMeasure7"), arr2[i].getString("strMeasure8"),
	    			arr2[i].getString("strMeasure9"), arr2[i].getString("strMeasure10"), arr2[i].getString("strMeasure11"), arr2[i].getString("strMeasure12"),
	    			arr2[i].getString("strMeasure13"), arr2[i].getString("strMeasure14"), arr2[i].getString("strMeasure15"), arr2[i].getString("strMeasure16"),
	    			arr2[i].getString("strMeasure17"), arr2[i].getString("strMeasure18"), arr2[i].getString("strMeasure19"), arr2[i].getString("strMeasure20"));
	    	Ingredients ingr = new Ingredients(arr2[i].getString("strIngredient1"),arr2[i].getString("strMeasure1"));
	    }
	    
	    idrecipetitle.setText(arr2[0].getString("strMeal"));
	    Image img3 = new Image(arr2[0].getString("strMealThumb"));
        idrecipeimage.setImage(img3);
        idrecipecategory.setText(arr2[0].getString("strCategory"));
        idrecipeinstructiontitle.setText("Recipe Instructions:");
        idrecipeinstructions.setText(arr2[0].getString("strInstructions"));
       
        WebEngine webEngine = idrecipewebview.getEngine();
        String test = arr2[0].getString("strYoutube");
        test = test.replaceAll(".+=", "");
        webEngine.load("https://www.youtube.com/watch?v=".concat(test));
        idrecipewebview.setPrefSize(640, 390);

        
        
        //add 10 ingredients and quantities
        for(int i = 0; i<recipes.length(); i++) {
        	Ingredients ingr1;
        	ingr1 = new Ingredients(recipeArr[i].getRecipeIng1(),recipeArr[i].getRecipeQty1());
        	ingredients.add(ingr1);
        	Ingredients ingr2;
        	ingr2 = new Ingredients(recipeArr[i].getRecipeIng2(),recipeArr[i].getRecipeQty2());
        	ingredients.add(ingr2);
        	Ingredients ingr3;
        	ingr3 = new Ingredients(recipeArr[i].getRecipeIng3(),recipeArr[i].getRecipeQty3());
        	ingredients.add(ingr3);
        	Ingredients ingr4;
        	ingr4 = new Ingredients(recipeArr[i].getRecipeIng4(),recipeArr[i].getRecipeQty4());
        	ingredients.add(ingr4);
        	Ingredients ingr5;
        	ingr5 = new Ingredients(recipeArr[i].getRecipeIng5(),recipeArr[i].getRecipeQty5());
        	ingredients.add(ingr5);
        	Ingredients ingr6;
        	ingr6 = new Ingredients(recipeArr[i].getRecipeIng6(),recipeArr[i].getRecipeQty6());
        	ingredients.add(ingr6);
        	Ingredients ingr7;
        	ingr7 = new Ingredients(recipeArr[i].getRecipeIng7(),recipeArr[i].getRecipeQty7());
        	ingredients.add(ingr7);
        	Ingredients ingr8;
        	ingr8 = new Ingredients(recipeArr[i].getRecipeIng8(),recipeArr[i].getRecipeQty8());
        	ingredients.add(ingr8);
        	Ingredients ingr9;
        	ingr9 = new Ingredients(recipeArr[i].getRecipeIng9(),recipeArr[i].getRecipeQty9());
        	ingredients.add(ingr9);
        	Ingredients ingr10;
        	ingr10 = new Ingredients(recipeArr[i].getRecipeIng10(),recipeArr[i].getRecipeQty10());
        	ingredients.add(ingr10);
        	Ingredients ingr11;
        	ingr11 = new Ingredients(recipeArr[i].getRecipeIng11(),recipeArr[i].getRecipeQty11());
        	ingredients.add(ingr11);
        	Ingredients ingr12;
        	ingr12 = new Ingredients(recipeArr[i].getRecipeIng12(),recipeArr[i].getRecipeQty12());
        	ingredients.add(ingr12);
        	Ingredients ingr13;
        	ingr13 = new Ingredients(recipeArr[i].getRecipeIng13(),recipeArr[i].getRecipeQty13());
        	ingredients.add(ingr13);
        	Ingredients ingr14;
        	ingr14 = new Ingredients(recipeArr[i].getRecipeIng14(),recipeArr[i].getRecipeQty14());
        	ingredients.add(ingr14);
        	Ingredients ingr15;
        	ingr15 = new Ingredients(recipeArr[i].getRecipeIng15(),recipeArr[i].getRecipeQty15());
        	ingredients.add(ingr15);
        	Ingredients ingr16;
        	ingr16 = new Ingredients(recipeArr[i].getRecipeIng16(),recipeArr[i].getRecipeQty16());
        	ingredients.add(ingr16);
        	Ingredients ingr17;
        	ingr17 = new Ingredients(recipeArr[i].getRecipeIng17(),recipeArr[i].getRecipeQty17());
        	ingredients.add(ingr17);
        	Ingredients ingr18;
        	ingr18 = new Ingredients(recipeArr[i].getRecipeIng18(),recipeArr[i].getRecipeQty18());
        	ingredients.add(ingr18);
        	Ingredients ingr19;
        	ingr19 = new Ingredients(recipeArr[i].getRecipeIng19(),recipeArr[i].getRecipeQty19());
        	ingredients.add(ingr19);
        	Ingredients ingr20;
        	ingr20 = new Ingredients(recipeArr[i].getRecipeIng20(),recipeArr[i].getRecipeQty20());
        	ingredients.add(ingr20);
        }
        idrecipeingredients.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("recipeIngredient"));
        idrecipequantity.setCellValueFactory(new PropertyValueFactory<Ingredients, String>("recipeQuantity"));
        idrecipetable.setItems(ingredients);
		
	}
    
    
}
