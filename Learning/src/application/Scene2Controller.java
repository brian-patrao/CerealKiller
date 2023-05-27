package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Scene2Controller implements Initializable {
	@FXML
	private TextField tfGrocery;
	@FXML
	private TextField tfQuantity;
	@FXML
	private TextField tfUseBy;
	@FXML
	private TableView<Groceries> tvGrocery;
	@FXML
	private TableColumn<Groceries, Integer> colid;
	@FXML
	private TableColumn<Groceries, String> colGrocery;
	@FXML
	private TableColumn<Groceries, String> colQuantity;
	@FXML
	private TableColumn<Groceries, String> colUseBy;
	@FXML
	private ImageView btnInsert;
	@FXML
	private ImageView btnDelete;
	@FXML
	private ImageView btnUpdate;
	@FXML
	private ImageView backButton;

	
	
	// Event Listener on Button.onAction
	@FXML
	public void switchToScene1(MouseEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		//we get the source of event and cast it to a node
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
    public Connection getConnection(){
        try{
        	String url = "jdbc:mysql://localhost:3306/Groceries";
        	String username = "root";
        	String password = "";
        	
        	Connection connection = DriverManager.getConnection(url, username, password);
        	
        	//string sql = "INSERT INTO customer (firstname, lastname)"
           
            System.out.println(connection);
            
            return connection;
            
            
            
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    public ObservableList<Groceries> getGroceriesList(){
    	ObservableList<Groceries> groceryList = FXCollections.observableArrayList();
    	Connection conn = getConnection();
    	String query = "SELECT * FROM groceries";
    	Statement st;
    	ResultSet rs;
    	
    	try {
    		st = conn.createStatement();
    		rs = st.executeQuery(query);
    		Groceries groceries;
    		while(rs.next()) {
    			groceries = new Groceries(rs.getInt("id"),rs.getString("grocery_name"),rs.getString("quantity"),rs.getString("useby"));
    			groceryList.add(groceries);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return groceryList;
    }
    
    public void showGroceries() {
    	ObservableList<Groceries> list = getGroceriesList();
    	
    	colid.setCellValueFactory(new PropertyValueFactory<Groceries, Integer>("id"));
    	colGrocery.setCellValueFactory(new PropertyValueFactory<Groceries, String>("groceryName"));
    	colQuantity.setCellValueFactory(new PropertyValueFactory<Groceries, String>("quantity"));
    	colUseBy.setCellValueFactory(new PropertyValueFactory<Groceries, String>("useBy"));
    	
    	tvGrocery.setItems(list);
    }
    
    private void insertRecord() {
    	String query = "INSERT INTO groceries (grocery_name,quantity, useby) VALUES (" + "'" + tfGrocery.getText() + "','" + tfQuantity.getText() + "','" + tfUseBy.getText() + "')";
    	System.out.println(query);
    	executeQuery(query);
    	showGroceries();
    }
    private void updateRecord(){
        String query = "UPDATE groceries SET grocery_name  = '" + tfGrocery.getText() + "', quantity = '" + tfQuantity.getText() + "', useby = '" +
        		tfUseBy.getText() + "' WHERE grocery_name = '" + tfGrocery.getText() + "'";
        System.out.println(query);
        executeQuery(query);
        showGroceries();
    }
    
    private void deleteButton(){
        String query = "DELETE FROM groceries WHERE grocery_name = '" + tfGrocery.getText() + "'";
        executeQuery(query);
        showGroceries();
    }
    
    public void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
	
	// Event Listener on Button[#btnInsert].onAction
	@FXML
	public void handleInsert(MouseEvent event) {
		insertRecord();
	}
	// Event Listener on Button[#btnDelete].onAction
	@FXML
	public void handleDelete(MouseEvent event) {
		deleteButton();
	}
	// Event Listener on Button[#btnUpdate].onAction
	@FXML
	public void handleUpdate(MouseEvent event) {
		updateRecord();
		
		
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showGroceries();
	}
}
