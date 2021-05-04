package commands;


import java.io.File;

import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class OpenDocument implements Action {

	private ReplayManager replayManager;
	private Stage mainStage;
	private CommandManager commandManager;
	private String encryption = "";
	private TextArea textArea;
	private File file = null;
	
	public OpenDocument(Stage stage, CommandManager manager) {
		mainStage = stage;
		commandManager = manager;
	}
	
	public void setCommandManager(CommandManager manager) {
		commandManager = manager;
	}
	
	public void setTextArea(TextArea area) {
		textArea = area;
	}
	
	public void setReplayManager(ReplayManager manager) {
		replayManager = manager;
	}
	public void setEncryption(String encr) {
		encryption = encr;
	}
	@Override
	public void handle() {
		loadFile();
		addToReplayManager();
	}
	public void setFile(File f) {
		file = f;
	}
	
	public Action clone() {
		OpenDocument doc =new OpenDocument(mainStage,commandManager);
		doc.setEncryption(encryption);
		doc.setTextArea(textArea);
		doc.setReplayManager(replayManager);
		doc.setFile(file);
		return doc;
	}
	
	@Override
	public void replayAction()
	{
		if (file != null) {
			String text = commandManager.openDocument(file, encryption);
			textArea.setText(text);
		}
	}
	
	public String toString() {
		return "Open: "+file.getAbsoluteFile(); 
	}
	
	private void addToReplayManager() {
		if (replayManager.isActiveRecording())
			replayManager.addAction(clone());
	}
		
	private void loadFile() {
		file = FileHandler.showLoadFileChooser(commandManager.getExtentionsList(), mainStage);		
		replayAction();
	}
}
