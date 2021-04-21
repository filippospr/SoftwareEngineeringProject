package gui;
import java.io.IOException;
 
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class GUIController extends Application{
	private Scene mainScene;
	public void start(Stage stage) throws IOException
    {
        // Create the FXMLLoader 
        // Path to the FXML File
        String path = "../MainScene.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(path));
        // Create the Scene
        mainScene = new Scene(root);
        // Set the Scene to the Stage
        stage.setScene(mainScene);
        // Set the Title to the Stage
        stage.setTitle("A simple FXML Example");
        // Display the Stage
        stage.show();
    }
	
	@FXML public MenuItem closeBtn;
	public void exitApp() {
		Platform.exit();
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}
}