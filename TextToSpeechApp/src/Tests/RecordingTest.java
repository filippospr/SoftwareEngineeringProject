package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import commands.CommandManager;
import commands.OpenDocument;
import commands.ReplayManager;

class RecordingTest {
	ReplayManager replayManager;
	CommandManager commandManager;
	
	@BeforeEach
	void setUp() {
		replayManager = new ReplayManager();
		commandManager = new CommandManager();
		replayManager.startRecording();
	}
	
	@Test
	void testStartRecording() {
		OpenDocument testAction = new OpenDocument(commandManager);
		testAction.setFile(new File(ClassLoader.getSystemResource("./test_files/testword1.docx").getFile()));
		testAction.setReplayManager(replayManager);
		testAction.action();
		assertEquals(1, replayManager.getCommandListSize());
		assertEquals(true, replayManager.isActiveRecording());
	}
	
	@Test
	void testEndRecording() {
		OpenDocument testAction = new OpenDocument(commandManager);
		testAction.setFile(new File(ClassLoader.getSystemResource("./test_files/testword1.docx").getFile()));
		testAction.setReplayManager(replayManager);
		testAction.action();
		replayManager.endRecording();
		assertEquals(0, replayManager.getCommandListSize());
		assertEquals(false, replayManager.isActiveRecording());
	}

}
