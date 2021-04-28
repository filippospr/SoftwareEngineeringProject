package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade {
	private VoiceManager vm;
	private Voice voice;
	public TTSFacade() {
		vm = VoiceManager.getInstance();
        voice = vm.getVoice("kevin");
	}
	
	public void play(String text) {
		voice.allocate();
		voice.speak(text);
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
	
	public static void main(String[] args) {
		TTSFacade tts = new TTSFacade();
		tts.play("makaronia");
	}
}
