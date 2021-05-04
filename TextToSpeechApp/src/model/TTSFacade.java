package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade{
	private VoiceManager vm;
	private Voice voice;
	private Thread speechThread;
	private static volatile boolean stop = false;
	
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
		    	
		    	while(!stop && words.length > w) {
		    		voice.speak(words[w++]);
		    	}	
		    }
		});
		speechThread.start();
	}
	
	public void stop() {
		stop = true;
		voice.deallocate();
	}
	
	public void setVolume(int volume) {
        voice.setVolume(volume);
	}
	
	public void setPitch(int pitch) {
		 voice.setPitch(pitch);
	}
	
	public void setRate(int rate) {
		voice.setRate(rate);
	}
}