package application;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import java.lang.Integer;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class TilePane extends StackPane {
	Rectangle back;
	Label label;
	public int duration;
	private String value = "";
	/*claude's code begins*/
	TranslateTransition fallTransition;
	public void setFallTransition(TranslateTransition transition) {
        this.fallTransition = transition;
    }
    
    public void speedUp() {
        if (fallTransition != null) {
            fallTransition.setRate(fallTransition.getRate() * 2);
        }
    }
    //end of claude's code
	
	public String getValue() {
		return value;
	}
	
	public TilePane(String val, double x, double y, int dur) {
		this.value = val;
		this.duration = dur;
		back = new Rectangle(80, 80);
		back.setStyle("-fx-stroke: black; -fx-stroke-width: 1; -fx-fill: lightgray;");
		label = new Label(val);
		label.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		this.getChildren().addAll(back, label);
		this.setLayoutX(x);
		this.setLayoutY(y);

	}
	
	
	public TilePane(int val, double x, double y) {
		this.value = Integer.toString(val);
		back = new Rectangle(50, 50);
		back.setStyle("-fx-stroke: black; -fx-stroke-width: 1; -fx-fill: lightgray;");
		label = new Label(value);
		this.getChildren().addAll(back, label);
		this.setLayoutX(x);
		this.setLayoutY(y);

	}
	

}
