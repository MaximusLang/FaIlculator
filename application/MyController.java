package application;
import javafx.scene.layout.*;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.lang.Math;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import javafx.fxml.FXML;


public class MyController {
	@FXML
	StackPane BasePane = new StackPane();
	
	@FXML
	public Pane TilesPane = new Pane();
	
	private void spawnTile() {
	    // Random value: 70% chance digit, 30% chance operator
	    String value;
	    if (Math.random() < 0.7) {
	        value = String.valueOf((int)(Math.random() * 10)); // 0-9
	    } else {
	        value = getRandomOperator(); // +, -, *, /
	    }
	    
	    // Random X position (keep tile within bounds)
	    double x = Math.random() * (TilesPane.getWidth() - 50);
	    
	    // Create tile at top (y = 0)
	    TilePane tile = new TilePane(value, x, 0, 7);
	    TilesPane.getChildren().add(tile);
	    
	    // Animate fall - CREATE THIS FIRST
	    TranslateTransition fall = new TranslateTransition(Duration.seconds(tile.duration), tile);
	    fall.setByY(TilesPane.getHeight());
	    fall.setOnFinished(e -> TilesPane.getChildren().remove(tile));
	    tile.setFallTransition(fall);  // Store reference in tile
	    fall.play();
	    
	    // Now set up hover behavior (AFTER fall exists)
	    tile.setOnMouseEntered(e -> {
	        if ((int)(Math.random()*10) == 7) {
	            TilesPane.getChildren().remove(tile); //10% chance of disappearing tile on mouse hover
	        }
	        else if ((int)(Math.random()*15) == 5) {
	            tile.speedUp();  // 6.7% chance tile will speed up on mouse hover
	        }
	    });
	   
	    tile.setOnMouseClicked(e -> {
	        if ((int)(Math.random()*10) == 7) {
	            if ((int)(Math.random()*4) == 1)
	                calcDisplay.setText(tile.getValue() + calcDisplay.getText()); //2.5% chance of concatenating w/o deleting & 7.5% chance of doing nothing
	        }
	        else {
	            // On click, remove tile and update display
	            TilesPane.getChildren().remove(tile);
	            if (calcDisplay.getText().equals("0")) {
	                calcDisplay.setText(tile.getValue());
	            } else {
	                calcDisplay.setText(calcDisplay.getText() + tile.getValue());
	            }
	        }
	    });
	}
    
    private String getRandomOperator() {
        String[] operators = {"+", "-", "*", "/"};
        return operators[(int)(Math.random() * 4)];
    }
	
	private Timeline spawner;
	
	@FXML
	HBox ControlPane = new HBox();
	
	@FXML
    Label calcDisplay = new Label();
	
	@FXML
	Button decimal = new Button(".");
	
	@FXML
	//adds decimals to string in display
	public void addDecimal(ActionEvent e) {
		try {
			calcDisplay.setText(calcDisplay.getText() + ".");
			} catch (Exception ex) {
				System.out.println("Error adding decimal: " + ex.getMessage());
			}
	}
	
	@FXML
	Button enter = new Button("Enter");
	
	@FXML
	//calculates and displays result in display with exception handling
	public void enter(ActionEvent e) {
		String result = "";
		try {
			//String expression = calcDisplay.getText();
			result = calculate(calcDisplay.getText());
			calcDisplay.setText(String.valueOf(result));
			} catch (Exception ex) {
				result = "Improper Expression";
			}
	}
	
	@FXML
	Button backspace = new Button("Backspace");
	
	@FXML
	public void backspace(ActionEvent e) {
		String currentText = calcDisplay.getText();
		if (currentText.length() > 0) {
			calcDisplay.setText(currentText.substring(0, currentText.length() - 1));
		}
		if (calcDisplay.getText().isEmpty()) {
			calcDisplay.setText("0");
		}
	}
	
	@FXML
	Button Clear = new Button();

	@FXML
	public void clear(ActionEvent e) {
	    calcDisplay.setText("0");
	}
	
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
	
	@FXML
	public String calculate(String expression) {
	    try {
	        // Parse expression into operators and operands
	        ArrayList<String> operands = new ArrayList<>();
	        ArrayList<String> operators = new ArrayList<>();
	        
	        String currentNumber = "";
	        for (int i = 0; i < expression.length(); i++) {
	            char c = expression.charAt(i);
	            
	            //adds character to current number if it is a digit OR if it's the first sign (+ or -)
	            if (Character.isDigit(c) || c == '.'
	            		|| (i == 0 && c == '+' || c == '-')) {
	                currentNumber += c;
	                
	            //ends current number and stores operator otherwise
	            } else if (c == '*' || c == '/' || c == '+' || c == '-') {
	                operands.add(currentNumber);
	                operators.add(String.valueOf(c));
	                currentNumber = "";
	            }
	        }
	        operands.add(currentNumber); // Add last number
	        
	        //Performs multiplication and division first between operands (following PEMDAS)
	        // PASS 1: Handle * and /
	        for (int i = 0; i < operators.size(); i++) {
	            if (operators.get(i).equals("*")) {
	                double result = Double.parseDouble(operands.get(i)) * Double.parseDouble(operands.get(i + 1));
	                operands.set(i, String.valueOf(result));
	                operands.remove(i + 1);
	                operators.remove(i);
	                i--; // Adjust index after removal
	            } else if (operators.get(i).equals("/")) {
	                double divisor = Double.parseDouble(operands.get(i + 1));
	                if (divisor == 0) return "Error: Div by 0";
	                double result = Double.parseDouble(operands.get(i)) / divisor;
	                operands.set(i, String.valueOf(result));
	                operands.remove(i + 1);
	                operators.remove(i);
	                i--; // Adjust index after removal
	            }
	        }
	        
	        //Performs addition and subtraction
	        // PASS 2: Handle + and -
	        for (int i = 0; i < operators.size(); i++) {
	            if (operators.get(i).equals("+")) {
	                double result = Double.parseDouble(operands.get(i)) + Double.parseDouble(operands.get(i + 1));
	                operands.set(i, String.valueOf(result));
	                operands.remove(i + 1);
	                operators.remove(i);
	                i--;
	            } else if (operators.get(i).equals("-")) {
	                double result = Double.parseDouble(operands.get(i)) - Double.parseDouble(operands.get(i + 1));
	                operands.set(i, String.valueOf(result));
	                operands.remove(i + 1);
	                operators.remove(i);
	                i--;
	            }
	        }
	        
	        // Should have one operand left - the result
	        return operands.get(0);
	        
	    } catch (Exception e) {
	        return "Error";
	    }
	}
	
	
}