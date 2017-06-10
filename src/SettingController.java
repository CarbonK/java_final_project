import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingController implements Initializable {
	
	@FXML
	private CheckBox dl_limit;
	@FXML
	private TextField dl_speed; 
	@FXML
	private CheckBox ul_limit;
	@FXML
	private TextField ul_speed;
	@FXML
	private Button cancel; 
	@FXML
	private Button save;
	
	private static boolean dll = false;
	private static double dls = 100.0;
	private static boolean ull = false;
	private static double uls = 100.0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		assert dl_limit != null : "fx:id\"dl_limit\" not found !";
		assert dl_speed != null : "fx:id\"dl_speed\" not found !";
		assert ul_limit != null : "fx:id\"ul_limit\" not found !";
		assert ul_speed != null : "fx:id\"ul_speed\" not found !";
		assert cancel != null : "fx:id\"cancel\" not found !";
		assert save != null : "fx:id\"save\" not found !";
		
		dl_limit.selectedProperty().set(dll);
		dl_limit.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (!oldValue && newValue) {
				dl_speed.setEditable(newValue);
			} else if (oldValue && !newValue) {
				dl_speed.setEditable(newValue);
				dl_speed.textProperty().set(String.valueOf(dls));
			}
		});
		
		dl_speed.textProperty().set(String.valueOf(dls));
		dl_speed.setEditable(dll);
		
		ul_limit.selectedProperty().set(ull);
		ul_limit.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (!oldValue && newValue) {
				ul_speed.setEditable(newValue);
			} else if (oldValue && !newValue) {
				ul_speed.setEditable(newValue);
				ul_speed.textProperty().set(String.valueOf(uls));
			}
		});
		
		ul_speed.textProperty().set(String.valueOf(uls));
		ul_speed.setEditable(ull);
		
		cancel.setOnAction((event) -> {
			((Stage)cancel.getScene().getWindow()).close();
		});
		
		save.setOnAction((event) -> {
			dll = dl_limit.selectedProperty().get();
			dls = Double.valueOf(dl_speed.textProperty().get());
			ull = ul_limit.selectedProperty().get();
			uls = Double.valueOf(ul_speed.textProperty().get());
			
			if (dll) {
				Main.fxml.getTask_window().getChildren().forEach((elem) -> {
					((Task)elem).getBt().setDownLimit(dls);
				});
			} else {
				Main.fxml.getTask_window().getChildren().forEach((elem) -> {
					((Task)elem).getBt().setDownLimit(Double.MAX_VALUE);
				});
			}
			
			if (ull) {
				Main.fxml.getTask_window().getChildren().forEach((elem) -> {
					((Task)elem).getBt().setUpLimit(uls);
				});
			} else {
				Main.fxml.getTask_window().getChildren().forEach((elem) -> {
					((Task)elem).getBt().setUpLimit(Double.MAX_VALUE);
				});
			}
						
			((Stage)save.getScene().getWindow()).close();
		});
	}

	public static boolean getDll() {
		return dll;
	}
	
	public static double getDls() {
		return dls;
	}

	public static boolean getUll() {
		return ull;
	}
	
	public static double getUls() {
		return uls;
	}
	
}
