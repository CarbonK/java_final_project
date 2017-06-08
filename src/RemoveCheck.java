import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RemoveCheck extends VBox {

	private final CheckBox checkBox = new CheckBox("Also delete .torrent file");
	private final Button cancel = new Button("Cancel");
	private final Button remove = new Button("Remove");
	
	public RemoveCheck() {
		getChildren().addAll(new Label("Remove file ?"), checkBox, new HBox());
		((HBox)getChildren().get(2)).getChildren().addAll(cancel, remove);
		
		cancel.setOnAction((event) -> {
			((Stage)cancel.getScene().getWindow()).close();
		});
		
		remove.setOnAction((event) -> {
			Main.fxml.getTask_window().getChildren().forEach((elem) -> {
				Task current = (Task)elem;
				
				if (current.getSelected().get()) {
					current.getBt().delete(checkBox.selectedProperty().get());
				}
			});
			Main.fxml.getTask_window().getChildren().removeIf((elem) -> {
				return ((Task)elem).getSelected().get();
			});
			((Stage)remove.getScene().getWindow()).close();
		});
	}
	
}
