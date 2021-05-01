package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade{
	private VoiceManager vm;
	private Voice voice;
	private Thread speechThread  = new Thread();
	
	public TTSFacade() {
		vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin");
        voice.allocate();
	}
	
	public void play(String text) {
		
		speechThread = new Thread(new Runnable() {
		    public void run() {
		    	boolean stop = false;
		    	try {
		    		String[] words = text.split(" ");
			    	int w = 0;
			    	
			    	while(!stop && words.length > w) {
			    		voice.speak(words[w++]);
			    		stop = Thread.interrupted();
			    	}
		    	}catch(Exception e){
		    	}
		    	
		    }
		});
		speechThread.start();
	}
	
	public void stop() {
		speechThread.interrupt();
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