package commands;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ReplayManager {
	private boolean recordingStatus;
	private ArrayList<EventHandler<ActionEvent>> commandList;
	
	public ReplayManager() {
		recordingStatus = false;
		commandList = new ArrayList<EventHandler<ActionEvent>>();
	}
	
	public void replay() {
		
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
	
	public void addAction(EventHandler<ActionEvent> actionListener) {
		
	}
}
