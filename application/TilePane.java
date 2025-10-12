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
	private String value = "";
	
	public String getValue() {
		return value;
	}
	
	public TilePane(String val, double x, double y) {
		this.value = val;
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
