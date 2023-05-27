package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class PlannerController implements Initializable  {

    @FXML
    private ImageView backPlanner;

    @FXML
    private ComboBox<String> cbBreakfast;

    @FXML
    private ComboBox<String> cbDay;

    @FXML
    private ComboBox<String> cbDinner;

    @FXML
    private ComboBox<String> cbLunch;
    
    @FXML
    private TableView<Planner> plannerView;

    @FXML
    private TableColumn<Planner, String> colBreakfast;

    @FXML
    private TableColumn<Planner, String> colDay;

    @FXML
    private TableColumn<Planner, String> colDinner;

    @FXML
    private TableColumn<Planner, String> colLunch;

    @FXML
    private ImageView updatePlan;
    
    

    @FXML
    void goToMainMenu(MouseEvent event) throws IOException {
    	Scene2Controller sc = new Scene2Controller();
    	sc.switchToScene1(event);
    }

    @FXML
    void updateDayPlan(MouseEvent event) {
    	Scene2Controller scene2Controller = new Scene2Controller();
    	String query = "UPDATE dayPlan SET day  = '" + cbDay.getValue() +
    			"', breakfast = '" + cbBreakfast.getValue() +
    			"', lunch = '" + cbLunch.getValue() +
    			"', dinner = '" + cbDinner.getValue() +
    			"' WHERE day = '" + cbDay.getValue() + "'";
        System.out.println(query);
        scene2Controller.executeQuery(query);
        showPlanner();

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ArrayList<String> favourites= new ArrayList<>();
		Scene2Controller scene2Controller = new Scene2Controller();
		Connection conn = scene2Controller.getConnection();
		String query = "SELECT mealname FROM favourites;";
		Statement st;
		ResultSet rs;
		
		try {
    		st = conn.createStatement();
    		rs = st.executeQuery(query);

    		while(rs.next()) {
    			favourites.add(rs.getString("mealname"));
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
		
		
		for(String lvs : favourites) {
			cbBreakfast.getItems().add(lvs);
			cbDinner.getItems().add(lvs);
			cbLunch.getItems().add(lvs);
	    }
		showPlanner();
		
	}
	
	private void showPlanner() {
		Scene2Controller scene2Controller = new Scene2Controller();
		Connection conn = scene2Controller.getConnection();
		ObservableList<Planner> plannerList =  FXCollections.observableArrayList();
		String query_planner = "SELECT * FROM dayPlan";
		Statement st2;
		ResultSet rs2;
		
		try{ 
			st2 = conn.createStatement();
			rs2 = st2.executeQuery(query_planner);
			Planner planner;
			while(rs2.next()) {
				planner = new Planner(rs2.getInt("id"), rs2.getString("day"),
						rs2.getString("breakfast"), rs2.getString("lunch"), rs2.getString("dinner"));
				plannerList.add(planner);
				cbDay.getItems().add(rs2.getString("day"));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		colDay.setCellValueFactory(new PropertyValueFactory<Planner, String>("day"));
		colBreakfast.setCellValueFactory(new PropertyValueFactory<Planner, String>("breakfast"));
		colLunch.setCellValueFactory(new PropertyValueFactory<Planner, String>("lunch"));
    	colDinner.setCellValueFactory(new PropertyValueFactory<Planner, String>("dinner"));
    	
    	plannerView.setItems(plannerList);	
		
	}

}
