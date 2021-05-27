package commands;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ReplayManager {
	private boolean recordingStatus;
	private ArrayList<Action> commandList;
	
	public ReplayManager() {
		recordingStatus = false;
		commandList = new ArrayList<Action>();
	}
	
	public void replay(int i) {
			commandList.get(i).replayAction();
	}
	
	public void replay(Stage mainStage)
	{
		Stage selectionMenu = new Stage();
		//stop resize,and stay on top of parent
		selectionMenu.setResizable(false);
		selectionMenu.initOwner(mainStage);
		selectionMenu.initModality(Modality.APPLICATION_MODAL);
		selectionMenu.setTitle("Choose transformation to replay");
		
		VBox container = new VBox();
		container.setAlignment(Pos.TOP_CENTER);
		container.setSpacing(10);
		
		
		//create replay list and add it to window
		ListView<Action> listView = new ListView<Action>();
		listView.getItems().addAll(commandList);
		listView.setFixedCellSize(40);
		listView.setStyle("-fx-font-size: 1.5em;");		
		container.getChildren().add(listView);
		
		//create and add buttons to scene
		HBox buttonContainer = new HBox();
		buttonContainer.setSpacing(10);
		buttonContainer.setAlignment(Pos.TOP_CENTER);
		Button selectButton = new Button("Replay");
		Button cancelButton = new Button("Cancel");
		selectButton.setMinWidth(100);
		selectButton.setMinHeight(40);
		cancelButton.setMinWidth(100);
		cancelButton.setMinHeight(40);
		
		buttonContainer.getChildren().addAll(selectButton, cancelButton);
		container.getChildren().add(buttonContainer);
		VBox.setMargin(buttonContainer, new Insets(0,0,10,0));
		
		//set actions for buttons
		selectButton.setOnAction((event) -> {
			int index = listView.getSelectionModel().getSelectedIndex();
			if (index >= 0)
				replay(index);
			selectionMenu.close();
		});
		cancelButton.setOnAction((event) -> {
			selectionMenu.close();
		});
		
		Scene selectionMenuScene = new Scene(container, 450, 350);
		selectionMenu.setScene(selectionMenuScene);
		selectionMenu.showAndWait();
	}
	
	public void startRecording()
	{
		recordingStatus = true;
	}
	
	public void endRecording() {
		commandList.clear();
		recordingStatus = false;
	}
	
	public boolean isActiveRecording() {
		return recordingStatus;
	}
	
	public void addAction(Action e) {
		commandList.add(e);
	}
	
	public int getCommandListSize() {
		return commandList.size();
	}
}
