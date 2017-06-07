import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class TorrentOptions extends VBox {

	private final Button dst = new Button("/home/");
	private final Button cancel = new Button("Cancel");
	private final Button open = new Button("Open");
	private String destination = new String("/home/");
	
	public TorrentOptions(File torrent) {
		getChildren().addAll(new HBox(), new HBox(), new HBox());
		((HBox)getChildren().get(0)).getChildren().addAll(new Label("Torrent file:"), new Label(torrent.getName()));
		((HBox)getChildren().get(1)).getChildren().addAll(new Label("Destination folder:"), dst);
		((HBox)getChildren().get(2)).getChildren().addAll(cancel, open);
		
		dst.setOnAction((event) -> {
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Select Destination Folder");
			Stage stage = new Stage();
			destination = directoryChooser.showDialog(stage).getAbsolutePath();
			dst.setText(destination);
		});
		
		cancel.setOnAction((event) -> {
			((Stage)cancel.getScene().getWindow()).close();
		});
		
		open.setOnAction((event) -> {
			Main.fxml.getTask_window().getChildren().add(new Task(torrent.getAbsolutePath(), destination));
			((Stage)open.getScene().getWindow()).close();
		});
	}
	
}
