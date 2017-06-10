import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainWindowController implements Initializable {
	
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
	private Button encrypt;
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
		assert encrypt != null : "fx:id\"encrypt\" not found !";
		assert task_window != null : "fx:id\"task_window\" not found !";

		((ScrollPane)mainwindow.getChildren().get(2)).prefHeightProperty().bind(mainwindow.heightProperty().subtract(100));
		
		open.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open a Torrent");
			Stage stage = new Stage();
			File file = fileChooser.showOpenDialog(stage);
			
			if (file != null) {
				stage.setScene(new Scene(new TorrentOptions(file).getRoot()));
				stage.show();
			}
		});

		start.setOnAction((event) -> {
			task_window.getChildren().forEach((elem) -> {
				Task task = (Task)elem;
				if (task.getSelected().get()) {
					task.getBt().start();
				}
			});
		});
		
		pause.setOnAction((event) -> {
			task_window.getChildren().forEach((elem) -> {
				Task task = (Task)elem;
				if (task.getSelected().get()) {
					task.getBt().pause();
				}
			});
		});
		
		remove.setOnAction((event) -> {
			Stage stage = new Stage();
			stage.setScene(new Scene(new RemoveCheck().getRoot()));
			stage.show();
		});
		
		settings.setOnAction((event) -> {
			Stage stage = new Stage();
			stage.setScene(new Scene(new Settings().getRoot()));
			stage.show();
		});
		
		encrypt.setOnAction((event) -> {
			Encrypto e = new Encrypto();
			e.setVisible(true);
		});

		toggleSwitch = new ToggleSwitch();
		controll_bar.getChildren().add(toggleSwitch);
		HBox.setMargin(toggleSwitch, new Insets(2));
	}
	
	public VBox getTask_window() {
		return task_window;
	}

}
