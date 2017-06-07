import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class ToggleSwitch extends HBox {

	private final Label label = new Label();
	private final Button button = new Button();
	private SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
	
	public ToggleSwitch() {
		label.setText("OFF");
		getChildren().addAll(label, button);
		
		setWidth(150);
		setAlignment(Pos.CENTER_LEFT);
		setStyle("-fx-background-color: grey; -fx-background-radius: 10px;");
		
		label.setAlignment(Pos.CENTER);
		label.setStyle("-fx-background-color: grey; -fx-background-radius: 10px 0px 0px 10px;");
		label.setFont(new Font("bold Courier New", 20));
		label.prefWidthProperty().bind(widthProperty().divide(2));
		label.prefHeightProperty().bind(heightProperty());
	
		button.setStyle("-fx-background-radius: 10px;");
		button.prefWidthProperty().bind(widthProperty().divide(2));
		button.prefHeightProperty().bind(heightProperty());
		
		button.setOnAction((event) -> {
			switchedOn.set(!switchedOn.get());
		});
		label.setOnMouseClicked((event) -> {
			switchedOn.set(!switchedOn.get());
		});
		
		switchedOn.addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				setStyle("-fx-background-color: orange; -fx-background-radius: 10px;");
				
				label.setText("ON");
				label.setStyle("-fx-background-color: orange; -fx-background-radius: 0px 10px 10px 0px;");
				label.toFront();
			} else {
				setStyle("-fx-background-color: grey; -fx-background-radius: 10px;");
				
				label.setText("OFF");
				label.setStyle("-fx-background-color: grey; -fx-background-radius: 10px 0px 0px 10px;");
				button.toFront();
			}
		});
	}
	
	public SimpleBooleanProperty getSwitchedOn() {
		return switchedOn;
	}
	
}
