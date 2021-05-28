package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import audioPlayer.AudioPlayerController;
import commands.CommandManager;
import commands.DocumentToSpeech;
import commands.ReplayManager;

class TextToSpeechTest {
	FakeTTS test;
	
	@BeforeEach
	public void setUp() {
		test= new FakeTTS();
	}
	
	@Test
	void documentToSpeechTest() {
		String text="This is a test";
		test.play(text);
		
		assertEquals(text,test.getContents());
	}
	
	@Test 
	void audioParametersTest() {
		
		AudioPlayerController controller =new AudioPlayerController();
		controller.setTTSFacade(test);
		//defaultrate  == 150
		assertEquals(150f,test.getRate());
		
		controller.setPitch(100);
		controller.setVolume(0);
		controller.SpeedUp();
		
		assertEquals(0,test.getVolume());
		assertEquals(100,test.getPitch());
		assertEquals(200f,test.getRate());
		//rate = rate*rate/newRate???
	}
	
	
	@Test
	void replayRecordedAction() {
		ReplayManager replay= new ReplayManager();
		replay.startRecording();
		
		File wordFile= new File(ClassLoader.getSystemResource("./test_files/testword1.docx").getFile());
		AudioPlayerController audio=new AudioPlayerController();
		
		FakeTTS fake = new FakeTTS();
		audio.setTTSFacade(fake);
		
		CommandManager manager= new CommandManager(audio);
		
		DocumentToSpeech doc2speech = new DocumentToSpeech(null, manager);
		doc2speech.setReplayManager(replay);
		String contents=manager.openDocument(wordFile,"");
		
		//play audio
		doc2speech.setText(contents);
		doc2speech.action();
	
		assertEquals(contents,fake.getContents());
		assertEquals(1,replay.getCommandListSize());
		
		replay.replay(0);
		
		assertEquals(contents,fake.getContents());
	}

}
