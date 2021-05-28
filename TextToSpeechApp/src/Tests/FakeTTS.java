package Tests;

import audioPlayer.TTSFacade;

public class FakeTTS extends TTSFacade {
	private String contents = "";

	public FakeTTS(){
		super();
	}
	
	public void play(String text) {
		contents = text;
		super.play(text);
	}
	
	public String getContents() {
		return contents;
	}
	
	
}