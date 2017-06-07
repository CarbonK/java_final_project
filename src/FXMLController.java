import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
	
	@FXML
	private VBox mainwindow;
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
	private VBox task_window;
	
	private ToggleSwitch toggleSwitch;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert mainwindow != null : "fx:id\"mainwindow\" not found !";
		assert controll_bar != null : "fx:id\"controll_bar\" not found !";
		assert open != null : "fx:id\"open\" not found !";
		assert start != null : "fx:id\"start\" not found !";
		assert pause != null : "fx:id\"pause\" not found !";
		assert remove != null : "fx:id\"remove\" not found !";
		assert settings != null : "fx:id\"settings\" not found !";
		assert selector != null : "fx:id\"selector\" not found !";
		assert filter != null : "fx:id\"filter\" not found !";
		assert task_window != null : "fx:id\"task_window\" not found !";
		
		setMainwindow();
		setOpen();
		setStart();
		setToggleSwitch();
	}
	
	private void setMainwindow() {
		((ScrollPane)mainwindow.getChildren().get(2)).prefHeightProperty().bind(mainwindow.heightProperty().subtract(100));
	}
	
	private void setOpen() {
		open.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open a Torrent");
			Stage stage = new Stage();
			File file = fileChooser.showOpenDialog(stage);
			
			if (file != null) {
				stage.setScene(new Scene(new TorrentOptions(file)));
				stage.show();
			}
		});
	}

	private void setStart() {
		start.setOnAction((event) -> {
			task_window.getChildren().forEach((elem) -> {
				Task current = (Task)elem;
				if (current.getSelected().get()) {
					current.getBt().resume();
				}
			});
		});
	}
	
	private void setPause() {
		pause.setOnAction((event) -> {
			task_window.getChildren().forEach((elem) -> {
				Task current = (Task)elem;
				if (current.getSelected().get()) {
					current.getBt().pause();
				}
			});
		});
	}
	
	private void setToggleSwitch() {
		toggleSwitch = new ToggleSwitch();
		controll_bar.getChildren().add(toggleSwitch);
		controll_bar.setMargin(toggleSwitch, new Insets(2));
	}
	
	public VBox getMainwindow() {
		return mainwindow;
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
	
	public VBox getTask_window() {
		return task_window;
	}

}
