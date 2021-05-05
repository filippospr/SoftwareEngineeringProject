package gui;

import commands.CommandManager;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import model.TTSFacade;

public class AudioPlayerController {
	private VBox audioControlBar;
	private CommandManager commandManager;
	private TTSFacade audioManager;
	private Button playButton;
	private Button fastForwardButton;
	private Button RewindButton;
	private boolean isPlaying = false;
	private float defaultSpeed;
	
	public AudioPlayerController(Button play, Button ff, Button rewind) {
		playButton = play;
		fastForwardButton = ff;
		RewindButton = rewind;
	}
	
	public void setAudioControlBar(VBox audioBar) {
		audioControlBar = audioBar;
	}
	
	public void setCommandManager(CommandManager manager) {
		commandManager = manager;
	}
	
	public void setAudioManager(TTSFacade manager) {
		audioManager = manager;
		defaultSpeed = audioManager.getRate();
		manager.setController(this);
	}
	
	public void toggleEnableAudioBar() {
		audioControlBar.setDisable(!audioControlBar.isDisabled());
	}
	
	public void pauseAudio() {
		if(isPlaying) {
			audioManager.pause();
			playButton.getStyleClass().remove("pausebutton");
			playButton.getStyleClass().add("playbutton");
		}else {
			audioManager.pause();
			playButton.getStyleClass().remove("playbutton");
			playButton.getStyleClass().add("pausebutton");
		}
		isPlaying = !isPlaying;
	}

	public void setPitch(int pitch) {
		// TODO Auto-generated method stub
		audioManager.setPitch(pitch);
	}

	public double getPitch() {
		return audioManager.getPitch();
	}

	public void setSpeed(float speed) {
		audioManager.setRate(speed);
		
	}

	public float getSpeed() {
		return audioManager.getRate();
	}

	public float getDefaultSpeed() {
		return defaultSpeed;
	}

	public void SpeedUp() {
		audioManager.setRate(defaultSpeed+defaultSpeed*0.25f);
	}

	public void SpeedDown() {
		audioManager.setRate(defaultSpeed-defaultSpeed*0.25f);
		
	}

	public void setVolume(float floatValue) {
		// TODO Auto-generated method stub
		audioManager.setVolume(floatValue);
	}

	public float getVolume() {
		return audioManager.getVolume();
	}
}
