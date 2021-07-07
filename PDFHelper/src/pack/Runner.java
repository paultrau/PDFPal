package pack;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;

public class Runner extends Application{
	private static Stage stg;
	public static String[] inputs;
	//JAVAFX
    @Override
    public void start(Stage primaryStage)throws Exception {
       try {
    	   stg = primaryStage;
    	   primaryStage.setResizable(false);
    	   Parent root = FXMLLoader.load(getClass().getResource("fxSource.fxml"));
    	   primaryStage.setTitle("PDF PAL");
    	   primaryStage.setScene(new Scene(root));
    	   primaryStage.show();
       }
       catch(Exception e) {
    	   e.printStackTrace(); 
       }
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
