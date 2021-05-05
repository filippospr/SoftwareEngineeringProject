package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import gui.AudioPlayerController;

public class TTSFacade{
	private VoiceManager vm;
	private Voice voice;
	private Thread speechThread;
	private static volatile String status = "paused";
	private AudioPlayerController controller;

	
	public TTSFacade() {
		vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin");
        voice.allocate();
	}
	
	public void play(String text) {
		speechThread = new Thread(new Runnable() {
		    public void run() {
	    		String[] words = text.split(" ");
		    	int w = 0;
		    	while(status != "stop" && words.length > w) {
		    		if (status == "playing")
		    			voice.speak(words[w++]);
		    	}
		    	controller.toggleEnableAudioBar();
		    	controller.pauseAudio();
		    }
		});
		speechThread.start();
	}
	
	public void pause() {
		if (status.equals("paused"))
			status = "playing";
		else if (status.equals("playing"))
			status = "paused";
	}
	
	public void setController(AudioPlayerController c) {
		controller = c;
	}
	
	public void stop() {
		status = "stop";
		voice.deallocate();
	}
	
	public void setVolume(float volume) {
        voice.setVolume(volume);
	}
	
	public void setPitch(int pitch) {
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
}