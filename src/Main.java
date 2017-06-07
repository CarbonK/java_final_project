import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
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
			
			fxml.getOpen().setOnAction((event) -> {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open file");
				File file = fileChooser.showOpenDialog(primaryStage);
				fxml.getTask_window().getChildren().add(new Task(file));
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
