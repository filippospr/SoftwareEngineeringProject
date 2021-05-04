package gui;

import java.io.IOException;

import commands.Action;
import commands.CommandManager;
import commands.CommandsFactory;
import commands.ReplayManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GUIController extends Application{
	private Stage mainStage;
	private CommandManager commandManager = new CommandManager();
	private ReplayManager replayManager = new ReplayManager();
	private CommandsFactory commandsFactory = new CommandsFactory(commandManager, mainStage);
	@FXML private MenuItem openFile;
	@FXML private TextArea textArea;
	@FXML private MenuItem recordingButton;
	
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
        stage.setScene(mainScene);
        
        stage.getIcons().add(new Image("gui_files/images/icon.png"));
        // Set the Title to the Stage
        stage.setTitle("Text2Speech App");
        
        // Display the Stage
        stage.show();
    }
	
	
	public void exitApp() {
		Platform.exit();
	}
	
	public void openDocument()
	{
		open("open");
	}

	public void openAtBashDocument()
	{
		open("openAtBash");
	}
	
	public void openRot13Document()
	{
		open("openRot13");
	}
	
	public void saveDocument()
	{
		save("save");
	}

	public void saveAtBashDocument()
	{
		save("saveAtBash");
	}
	
	public void saveRot13Document()
	{
		save("saveRot13");
	}
	
	public void transformText() {
		Action op = commandsFactory.createCommand("speech");
		op.setReplayManager(replayManager);
		op.setTextArea(textArea);
		op.handle();
	}
	
	public void toggleRecordingStatus() {
		if(replayManager.isActiveRecording()){
			recordingButton.setText("Start Recording");
			replayManager.endRecording();
		} else {
			recordingButton.setText("End Recording");
			replayManager.startRecording();
		}		
	}
	
	public void replay()
	{
		Stage selectionMenu = new Stage();
		//stop resize,and stay on top of parent
		selectionMenu.setResizable(false);
		selectionMenu.initOwner(mainStage);
		selectionMenu.initModality(Modality.APPLICATION_MODAL);
		selectionMenu.setTitle("Choose transformation to replay");
		
		VBox container = new VBox();
		container.setAlignment(Pos.TOP_CENTER);
		container.setSpacing(10);
		
		
		//create replay list and add it to window
		ListView<Action> listView = new ListView<Action>();
		listView.getItems().addAll(replayManager.getCommands());
		listView.setFixedCellSize(40);
		listView.setStyle("-fx-font-size: 1.5em ;");		
		container.getChildren().add(listView);
		
		//create and add buttons to scene
		HBox buttonContainer = new HBox();
		buttonContainer.setSpacing(10);
		buttonContainer.setAlignment(Pos.TOP_CENTER);
		Button selectButton = new Button("Replay");
		Button cancelButton = new Button("Cancel");
		selectButton.setMinWidth(100);
		selectButton.setMinHeight(40);
		cancelButton.setMinWidth(100);
		cancelButton.setMinHeight(40);
		buttonContainer.getChildren().addAll(selectButton, cancelButton);
		container.getChildren().add(buttonContainer);
		VBox.setMargin(buttonContainer, new Insets(0,0,10,0));
		
		//set actions for buttons
		selectButton.setOnAction((event) -> {
			int index = listView.getSelectionModel().getSelectedIndex();
			if (index >= 0)
				replayManager.replay(index);
			selectionMenu.close();
		});
		cancelButton.setOnAction((event) -> {
			selectionMenu.close();
		});
		
		Scene selectionMenuScene = new Scene(container, 450, 350);
		selectionMenu.setScene(selectionMenuScene);
		selectionMenu.showAndWait();
	}
	
	public void save(String encryption) {
        Action op = commandsFactory.createCommand(encryption);
		op.setReplayManager(replayManager);
		op.setTextArea(textArea);
		op.handle();
	}
	
	private void open(String encryption) {
		Action op = commandsFactory.createCommand(encryption);
		op.setReplayManager(replayManager);
		op.setTextArea(textArea);
		op.handle();
	}
	
	
	
	//is called when the window is closed
	@Override
	public void stop(){
		commandManager.stop(); 
	}
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}	
}