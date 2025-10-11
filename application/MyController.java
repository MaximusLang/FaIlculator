package application;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.fxml.FXML;

public class MyController {
	@FXML
	StackPane BasePane = new StackPane();
	
	@FXML
	Pane TilesPane = new Pane();
	
	@FXML
	HBox ControlPane = new HBox();
	
	@FXML
    Label calcDisplay = new Label("0");
	
	@FXML
	Button decimal = new Button(".");
	
	@FXML
	Button enter = new Button("Enter");
	
	@FXML
	Button backspace = new Button("Backspace");
	
	@FXML
	public void initialize() {
		// Initialization code here
	}
}