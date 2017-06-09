import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class RemoveCheck {
	
	private Parent root; 
	
	public RemoveCheck() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoveCheck.fxml"));
			root = loader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Parent getRoot() {
		return root;
	}
	
}
