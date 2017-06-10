import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TorrentOptionsController implements Initializable {

	@FXML
	private Button torrent;
	@FXML
	private Button dst;
	@FXML
	private CheckBox share;
	@FXML
	private Button cancel;
	@FXML
	private Button open;
	
	private String path = new String();
	private String destination = new String();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert torrent != null : "fx:id\"torrent\" not found !";
		assert dst != null : "fx:id\"dst\" not found !";
		assert share != null : "fx:id\"share\" not found !";
		assert cancel != null : "fx:id\"cancel\" not found !";
		assert open != null : "fx:id\"open\" not found !";
		
		torrent.setOnAction((event) -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open a Torrent");
			File file = fileChooser.showOpenDialog(new Stage());
			
			if (file != null) {
				path = file.getAbsolutePath();
				torrent.setText(path);
			}
		});
		
		dst.setOnAction((event) -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Select Destination Folder");
			File file = directoryChooser.showDialog(new Stage());
			
			if (file != null) {
				destination = file.getAbsolutePath();
				dst.setText(destination);
			}
		});
		
		cancel.setOnAction((event) -> {
			((Stage)open.getScene().getWindow()).close();
		});
		
		open.setOnAction((event) -> {
			Main.fxml.getTask_window().getChildren().add(new Task(path, destination, share.selectedProperty().get()));
			((Stage)open.getScene().getWindow()).close();
		});
	}
	
	public void setPath(String path) {
		this.path = path;
		torrent.setText(path);
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
		dst.setText(destination);
	}
	
}
