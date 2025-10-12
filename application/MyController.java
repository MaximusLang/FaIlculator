package application;
import javafx.scene.layout.*;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.lang.Math;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.scene.shape.Line;


import javafx.fxml.FXML;


public class MyController {
	@FXML
	StackPane BasePane = new StackPane();
	
	@FXML
	public Pane TilesPane = new Pane();
	
	private void spawnTile() {
        // Random value: 70% digit, 30% operator
        String value;
        if (Math.random() < 0.7) {
            value = String.valueOf((int)(Math.random() * 10)); // 0-9
        } else {
            value = getRandomOperator(); // +, -, *, /
        }
        
        // Random X position (keep tile within bounds)
        double x = Math.random() * (TilesPane.getWidth() - 50);
        
        // Create tile at top (y = 0)
        TilePane tile = new TilePane(value, x, 0);
        TilesPane.getChildren().add(tile);
        tile.setOnMouseClicked(e -> {
			// On click, remove tile and update display
			TilesPane.getChildren().remove(tile);
			if (calcDisplay.getText().equals("0")) {
				calcDisplay.setText(tile.getValue());
			} else {
				calcDisplay.setText(calcDisplay.getText() + tile.getValue());
			}
		});
        
        // Animate fall
        TranslateTransition fall = new TranslateTransition(Duration.seconds(7), tile);
        fall.setByY(TilesPane.getHeight());
        fall.setOnFinished(e -> TilesPane.getChildren().remove(tile)); // Remove when reaches bottom
        fall.play();
    }
    
    private String getRandomOperator() {
        String[] operators = {"+", "-", "*", "/"};
        return operators[(int)(Math.random() * 4)];
    }
	
	private Timeline spawner;
	
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
		int x = (int)Math.random()*9;
		startSpawning();
		
	}
	
	public void startSpawning() {
		spawner = new Timeline(new KeyFrame(Duration.seconds(0.7), event -> spawnTile()));
		spawner.setCycleCount(Timeline.INDEFINITE);
		spawner.play();
	}
}