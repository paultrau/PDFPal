package pack;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	public static void display(String title, String message) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setWidth(450);
		window.setHeight(150);
		
		Label label = new Label();
		label.setText(message);
		//Button closeButton = new Button("Close Window.");
		//closeButton.setOnAction(e-> window.close());
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, new Label("You may close the software now."));
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
	}

}
