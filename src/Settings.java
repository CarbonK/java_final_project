import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Settings {

	private Parent root;
	
	public Settings() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
			root = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Parent getRoot() {
		return root;
	}
	
}
