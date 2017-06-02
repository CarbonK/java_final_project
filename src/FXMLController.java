import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class FXMLController implements Initializable {
	
	@FXML
	private HBox controll_bar;
	@FXML
	private Button open;
	@FXML
	private Button start;
	@FXML
	private Button pause;
	@FXML
	private Button remove;
	@FXML
	private Button settings;
	@FXML
	private ChoiceBox<Pane> selector;
	@FXML
	private TextField filter;
	@FXML
	private ScrollPane task_window;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert controll_bar != null : "fx:id\"controll_bar\" not found !";
		assert open != null : "fx:id\"open\" not found !";
		assert start != null : "fx:id\"start\" not found !";
		assert pause != null : "fx:id\"pause\" not found !";
		assert remove != null : "fx:id\"remove\" not found !";
		assert settings != null : "fx:id\"settings\" not found !";
		assert selector != null : "fx:id\"selector\" not found !";
		assert filter != null : "fx:id\"filter\" not found !";
		assert task_window != null : "fx:id\"task_window\" not found !";
	}
	
	public HBox getControll_bar() {
		return controll_bar;
	}
	
	public Button getOpen() {
		return open;
	}
	
	public Button getStart() {
		return start;
	}
	
	
	public Button getPause() {
		return pause;
	}
	
	public Button getRemove() {
		return remove;
	}
	
	public Button getSettings() {
		return settings;
	}
	
	public ChoiceBox<Pane> getSelector() {
		return selector;
	}
	
	public TextField getFilter() {
		return filter;
	}
	
	public ScrollPane getTask_window() {
		return task_window;
	}

}
