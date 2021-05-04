package commands;

import java.io.File;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SaveDocument implements Action {
	
	private ReplayManager replayManager;
	private CommandManager commandManager;
	private Stage mainStage;
	private File file;
	private TextArea textArea;
	private String encryption;
	
	public SaveDocument(Stage stage, CommandManager manager){
		mainStage = stage;
		commandManager = manager;
	}
	
	public void setReplayManager(ReplayManager replayManager) {
		this.replayManager = replayManager;
	}

	public void setEncryption(String encr) {
		encryption = encr;
	}
	@Override
	public void replayAction() {
		commandManager.setContents(textArea.getText().split("\n"));
		commandManager.saveDocument(file, encryption);
	}

	@Override
	public void handle() {
		saveFile();
		addToReplayManager();
	}

	@Override
	public void setTextArea(TextArea textArea) {
		this.textArea = textArea;
		
	}
	
	@Override
	public Action clone() {
		SaveDocument doc =new SaveDocument(mainStage,commandManager);
		doc.setEncryption(encryption);
		doc.setTextArea(textArea);
		doc.setReplayManager(replayManager);
		doc.file = file;
		return doc;
	}
	
	private void saveFile() {
		file = FileHandler.showSaveFileChooser(commandManager.getExtentionsList(), mainStage);
		if (file != null)
			replayAction();
	}
	
	private void addToReplayManager() {
		if (replayManager.isActiveRecording())
			replayManager.addAction(clone());
	}
	
	
	public String toString() {
		return "Save: "+file.getAbsoluteFile(); 
	}

}
