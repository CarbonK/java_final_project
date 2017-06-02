import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static FXMLController fxml = null;

	public static void main(String[] args) {
		Application.launch(Main.class, (String[])null);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			fxml = loader.getController();
			
			ToggleSwitch toggleSwitch = new ToggleSwitch();
			fxml.getControll_bar().getChildren().add(toggleSwitch);
			fxml.getControll_bar().setMargin(toggleSwitch, new Insets(2, 2, 2, 2));
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
