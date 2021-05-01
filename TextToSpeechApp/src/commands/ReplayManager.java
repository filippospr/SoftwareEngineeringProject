package commands;

import java.util.ArrayList;

public class ReplayManager {
	private boolean recordingStatus;
	private ArrayList<Action> commandList;
	
	public ReplayManager() {
		recordingStatus = false;
		commandList = new ArrayList<Action>();
	}
	
	public void replay() {
		for(Action a:commandList) {
			a.replayAction();
		}
	}
	
	public void replay(int i) {
			commandList.get(i).replayAction();
	}
	
	public ArrayList<Action> getCommands(){
		return commandList;
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
}
