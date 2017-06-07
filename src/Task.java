import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Task extends HBox {
	
	private final Label icon = new Label();
	private final VBox vBox = new VBox();
	private final Label name = new Label();
	private final Label progress = new Label();
	private final ProgressBar progressBar = new ProgressBar();
	private final Label speed = new Label();
	private final SimpleBooleanProperty selected = new SimpleBooleanProperty(false);
	private final Timer timer = new Timer();
	
	public Task(File file) {
		getChildren().addAll(icon, vBox);
		vBox.getChildren().addAll(name, progress, progressBar, speed);
		
		setMargin(vBox, new Insets(2));
		setHeight(100);
		setOnMouseClicked((event) -> {
			((VBox)getParent()).getChildren().forEach((elem) -> {
				((Task)elem).getSelected().set(false);;
			});
			selected.set(true);
		});
		
		vBox.setMargin(progressBar, new Insets(0, 10, 0, 10));
		vBox.prefWidthProperty().bind(this.widthProperty().subtract(50));
		
		icon.setPrefWidth(50);
		icon.setPrefHeight(100);
		icon.setStyle("-fx-background-image: url(\"./img/folder-symbol.png\"); -fx-background-repeat: no-repeat; -fx-background-position: center;");

		name.setPrefHeight(25);
		name.setFont(new Font("Courier New", 14).font("Courier", FontWeight.BOLD, 14));
		
		progress.setPrefHeight(25);
		progress.setText("None of " + "%) - Remaining time unknown");
		
		progressBar.prefWidthProperty().bind(vBox.widthProperty());
		progressBar.setPrefHeight(25);
		progressBar.setProgress(0);
		
		speed.setPrefHeight(25);
		speed.setText("Downloading from 0 of 0 peers");
		
		selected.addListener((observable, oldValue, newValue) -> {
			if (!oldValue && newValue) {
				setStyle("-fx-background-color: orange;");
			} else if (oldValue && !newValue) {
				setStyle("-fx-background-color: transparent;");
			}
		});
		
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
			}
			
		}, 0, 1000);
	}
		
	public SimpleBooleanProperty getSelected() {
		return selected;
	}

}