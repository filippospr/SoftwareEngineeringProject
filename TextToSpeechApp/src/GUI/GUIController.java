package gui;

import java.io.IOException;

import audioPlayer.AudioPlayerController;
import commands.Action;
import commands.CommandManager;
import commands.CommandsFactory;
import commands.ReplayManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIController extends Application{
	private Stage mainStage;
	private AudioPlayerController audioBarController = new AudioPlayerController();
	private ReplayManager replayManager = new ReplayManager();
	//private CommandManager commandManager = new CommandManager(audioBarController);
	private CommandsFactory commandsFactory = new CommandsFactory(new CommandManager(audioBarController), mainStage);
	
	private boolean firstTransform = true;
	
	@FXML private TextArea textArea;
	@FXML private MenuItem recordingButton;
	@FXML private VBox audioControlBar;
	@FXML private Button playButton;
	@FXML private Slider pitchSelector;
	@FXML private Slider volumeSlider;
	@FXML private Label speedLabel;
	
	
	
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
	
	public void pauseAudio(){
		
		audioBarController.toggleAudioPlayback();
	}

	public void transformText() {
		pitchSelector.setValue(audioBarController.getPitch());
		if (firstTransform) {
			initAudioBar();
			firstTransform = false;
		}
		audioControlBar.setDisable(false);
		volumeSlider.setValue(audioBarController.getVolume());
		Action op = commandsFactory.createCommand("speech");
		op.setReplayManager(replayManager);
		op.setTextArea(textArea);
		op.handle();
	}
	
	public void initAudioBar() {
		audioBarController.setPlayButton(playButton);
		audioBarController.setAudioControlBar(audioControlBar);
		pitchSelector.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
            			audioBarController.setPitch(new_val.intValue());
                }
            });
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                    Number old_val, Number new_val) {
            			audioBarController.setVolume(new_val.floatValue());
                }
            });
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
	
	public void mute() {
		audioBarController.setVolume(0);
		volumeSlider.setValue(0);
	}
	
	public void speedUp() {
		float speed = Float.parseFloat(speedLabel.getText().substring(1));
		
		if (speed < 3) {
			speed = Math.min(speed+0.25f,3);
			speedLabel.setText("x"+speed);
			audioBarController.SpeedUp();
		}		
	}
	
	public void speedDown() {
		float speed = Float.parseFloat(speedLabel.getText().substring(1));
		
		if (speed > 0.25) {
			speed = Math.max(speed-0.25f,0.25f);
			speedLabel.setText("x"+speed);
			audioBarController.SpeedDown();
		}		
	}
	
	public void replay()
	{
		replayManager.replay(mainStage);
	}
	
	public void save(String encryption) {
        Action op = commandsFactory.createCommand(encryption);
		op.setReplayManager(replayManager);
		op.setTextArea(textArea);
		op.handle();
	}
	
	//is called when the window is closed
	@Override
	public void stop(){
		audioBarController.stop(); 
	}
	
	private void open(String encryption) {
		Action op = commandsFactory.createCommand(encryption);
		op.setReplayManager(replayManager);
		op.setTextArea(textArea);
		op.handle();
	}
	
	
	public static void main(String[] args) {
		
		Application.launch(args);
	}	
}