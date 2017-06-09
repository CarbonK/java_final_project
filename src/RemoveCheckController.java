import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

public class RemoveCheckController implements Initializable {

	@FXML
	private CheckBox rm_torrent;
	@FXML
	private Button cancel;
	@FXML
	private Button remove;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert rm_torrent != null : "fx:id\"rm_torrent\" not found !";
		assert remove != null : "fx:id\"remove\" not found !";
		
		cancel.setOnAction((event) -> {
			((Stage)cancel.getScene().getWindow()).close();
		});
				
		remove.setOnAction((event) -> {
			Main.fxml.getTask_window().getChildren().forEach((elem) -> {
				Task task = (Task)elem;
				
				if (task.getSelected().get()) {
					task.getBt().delete(rm_torrent.selectedProperty().get());
				}
			});
			Main.fxml.getTask_window().getChildren().removeIf((elem) -> {
				return ((Task)elem).getSelected().get();
			});
			((Stage)remove.getScene().getWindow()).close();
		});
	}
	
}
