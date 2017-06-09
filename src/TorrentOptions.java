import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class TorrentOptions {

	private Parent root;
	
	public TorrentOptions(File file) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TorrentOptions.fxml"));
			root = loader.load();
			
			TorrentOptionsController controller = loader.getController();
			controller.setPath(file.getAbsolutePath());
			controller.setDestination(file.getParentFile().getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Parent getRoot() {
		return root;
	}
	
}
