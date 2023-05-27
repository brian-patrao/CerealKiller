package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Button btnLogin;

	// Event Listener on Button[#btnLogin].onAction
	@FXML
	public void checkLogin(ActionEvent event) throws IOException {
		String userid = username.getText();
		String passwrd = password.getText();
		
		
		Connection conn = getConnection();
		String query = "SELECT USERNAME FROM users WHERE username ='"+userid+"' AND password='"+passwrd+"';";
		Statement st;
		ResultSet rs;
		
		try {
    		st = conn.createStatement();
    		rs = st.executeQuery(query);

    		if(rs.next()) {
				Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				//we get the source of event and cast it to a node
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
			}else {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Wrong Credentials");
				a.show();
			}	
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	
	}
	
	public Connection getConnection(){
        try{
        	String url = "jdbc:mysql://localhost:3306/Groceries";
        	String username = "root";
        	String password = "";
        	
        	Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println(connection);
            return connection;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
