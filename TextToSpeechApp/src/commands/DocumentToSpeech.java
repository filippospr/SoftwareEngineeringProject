package commands;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DocumentToSpeech implements Action  {

	private ReplayManager replayManager;
	private Stage mainStage;
	private CommandManager commandManager;
	private TextArea textArea;
	private String text;
	
	public DocumentToSpeech(Stage stage, CommandManager manager){
		mainStage = stage;
		commandManager = manager;
	}
	
	@Override
	public void setReplayManager(ReplayManager replayManager) {
		this.replayManager = replayManager;
	}
	
	@Override
	public void setTextArea(TextArea area) {
		textArea = area;
	}

	@Override
	public void replayAction() {
		commandManager.play(text);
	}

	@Override
	public void handle() {
		//if we have selected some text ask what to transform
		if (textArea.getSelectedText().length() > 0){
			showTransformPopUp();
		}else {
			commandManager.setTransformStatus(0);
		}
		
		int transformStatus = commandManager.getTransformStatus();
		
		if (transformStatus == -1) {
			System.out.println("Canceled");
			return;
		}
		text =  textArea.getText();
		if (transformStatus == 1) {
			text = textArea.getSelectedText();
		}
		replayAction();
		commandManager.setTransformStatus(-1);
		addToReplayManager();
		
	}
	@Override
	public Action clone() {
		DocumentToSpeech doc =new DocumentToSpeech(mainStage,commandManager);
		doc.setTextArea(textArea);
		doc.setReplayManager(replayManager);
		doc.text = text;
		return doc;
	}
	
	public String toString() {
		String[] tList = text.split(" ");
		return String.format("Transform: %s... (%d words)", tList[0], tList.length);
	}
	
	private void addToReplayManager() {
		if (replayManager.isActiveRecording())
			replayManager.addAction(clone());
	}
	
	private void showTransformPopUp()
	{
		Stage parent = mainStage;
		Stage newStage = new Stage();
		newStage.setTitle("Transform only selected text?");
		//stop resize,and stay on top of parent
		newStage.setResizable(false);
		newStage.initOwner(parent);
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initStyle(StageStyle.UTILITY);
		
		VBox container = new VBox();
		container.setAlignment(Pos.CENTER);
		container.setSpacing(10);
		
		Label label = new Label(String.format("Current Selection Length: %d characters", textArea.getSelectedText().length()));
		container.getChildren().add(label);
		
		HBox buttonLayout = new HBox();
		buttonLayout.setAlignment(Pos.CENTER);
		
		Button allButton = new Button("All text");
		Button selectedButton = new Button("Selection");
		selectedButton.setMinSize(100, 50);
		allButton.setMinSize(100, 50);
		
		buttonLayout.getChildren().add(allButton);
		buttonLayout.getChildren().add(selectedButton);
		selectedButton.setOnAction((event) -> {
			commandManager.setTransformStatus(1);
			newStage.close();
		});
		allButton.setOnAction((event) -> {
			commandManager.setTransformStatus(0);
			newStage.close();
		});
		
		
		container.getChildren().add(buttonLayout);
		Scene stageScene = new Scene(container, 300, 150);
		newStage.setScene(stageScene);
		
		//primary window waits for new window to close
		newStage.showAndWait();
	}

}
