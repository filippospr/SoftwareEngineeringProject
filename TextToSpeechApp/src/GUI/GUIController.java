package gui;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import commands.CommandsFactory;
import commands.OpenDocument;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUIController extends Application{
	private Stage mainStage;
	@FXML public MenuItem openFile;
	@FXML public TextArea textArea;
	public void start(Stage stage) throws IOException
    {
		mainStage = stage;
        // Create the FXMLLoader 
        // Path to the FXML File
        String path = "../MainScene.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(path));
        // Create the Scene
        Scene mainScene = new Scene(root);
        // Set the Scene to the Stage
        //mainScene.;
        stage.setScene(mainScene);
        // Set the Title to the Stage
        stage.setTitle("A simple FXML Example");
        
        // Display the Stage
        stage.show();
    }
	
	
	public void exitApp() {
		Platform.exit();
	}
	
	public void openDocument()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(
				new FileChooser.ExtensionFilter("Word Document", Arrays.asList("*.docx","*.doc ","*.docm"))
            );
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(mainStage);
		if (file != null) {
			displayText(file.getName());
			//System.out.println(file.getName());
		}
	}
	
	public void displayText(String text)
	{
		textArea.setText(text);
	}
	
	public void replay()
	{
		//openFile.fire();
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}
}