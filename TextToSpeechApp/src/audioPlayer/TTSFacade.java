package audioPlayer;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade{
	private VoiceManager vm;
	private Voice voice;
	private Thread speechThread;
	private AudioPlayerController controller;
	
	//control variable for thread
	private static volatile String status = "stop";
	
	//default values
	private float defaultRate;
	private float defaultPitch;
	private float defaultVolume;

	
	public TTSFacade() {
		vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin");
        voice.allocate();
        defaultRate = voice.getRate();
        defaultPitch = voice.getPitch();
        defaultVolume = voice.getVolume();
	}
	
	public void play(String text) {
		speechThread = new Thread(new Runnable() {
		    public void run() {
		    	//if a transformation is already playing dont play another one
		    	if (status.equals("playing"))
		    		return;
		    	status = "playing";
	    		String[] words = text.split(" ");
		    	int w = 0;
		    	while(status != "stop" && words.length > w) {
		    		if (status == "playing")
		    			voice.speak(words[w++]);
		    	}
		    	controller.disableAudioBar();
		    	controller.toggleAudioPlayback();
		    	resetVoice();
		    	status="stop";
		    }
		});
		speechThread.start();
	}
	
	public void pause() {
		status = "paused";
	}
	
	public void resume() {
		if (status.equals("paused"))
			status = "playing";
	}
	
	public void setController(AudioPlayerController c) {
		controller = c;
	}
	
	public void stop() {
		status = "stop";
		voice.deallocate();
	}
	
	public void resetVoice() {
		setVolume(defaultVolume);
		setPitch(defaultPitch);
		setRate(defaultRate);
	}
	
	public void setVolume(float volume) {
        voice.setVolume(volume);
	}
	
	public void setPitch(float pitch) {
		 voice.setPitch(pitch);
	}
	
	public void setRate(float rate) {
		voice.setRate(rate);
	}

	public double getPitch() {
		return voice.getPitch();
	}

	public float getRate() {
		return voice.getRate();
	}

	public float getVolume() {
		return voice.getVolume();
	}
	
	public boolean isPlaying() {
		return status.equals("playing");
	}
}