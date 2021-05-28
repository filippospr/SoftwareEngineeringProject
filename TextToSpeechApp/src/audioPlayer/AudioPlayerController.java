package audioPlayer;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AudioPlayerController {
	private VBox audioControlBar;
	private TTSFacade audioManager;
	private float defaultSpeed;
	private Button playButton;
	
	public AudioPlayerController() {
		setTTSFacade(new TTSFacade());
	}
	
	public void setTTSFacade(TTSFacade facade) {
		audioManager = facade;
		audioManager.setController(this);
		defaultSpeed = audioManager.getRate();
	}
	
	public void setAudioControlBar(VBox audioBar) {
		audioControlBar = audioBar;
	}
	
	public void setPlayButton(Button button) {
		playButton = button;
	}
	
	public void disableAudioBar() {
	   audioControlBar.setDisable(true);
	}
	
	public void play(String text) {
		audioManager.play(text);
		if (audioControlBar != null) {
			audioControlBar.setDisable(false);
			playAudio();
		}
	}
	
	public void stop() {
		audioManager.stop();
	}
	
	public void SpeedUp() {
		setSpeed(defaultSpeed-defaultSpeed*0.25f);
	}

	public void SpeedDown() {
		setSpeed(defaultSpeed+defaultSpeed*0.25f);
	}

	public void setPitch(int pitch) {
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

	public void setVolume(float floatValue) {
		audioManager.setVolume(floatValue);
	}

	public float getVolume() {
		return audioManager.getVolume();
	}

	public void toggleAudioPlayback() {
		if (audioManager.isPlaying()) {
			pauseAudio();
		} else {
			playAudio();
		}
		
	}
	
	private void pauseAudio() {
		playButton.getStyleClass().remove("pausebutton");
		playButton.getStyleClass().add("playbutton");
		audioManager.pause();
	}
	
	private void playAudio() {
		playButton.getStyleClass().remove("playbutton");
		playButton.getStyleClass().add("pausebutton");
		audioManager.resume();
	}
}
