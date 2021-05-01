package commands;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Document;

public class SaveDocument implements Action {

	private Document document;
	private ReplayManager replayManager;
	public SaveDocument(){
		
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}
	
	public void setReplayManager(ReplayManager replayManager) {
		this.replayManager = replayManager;
	}
	
	@Override
	public void handle(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void replayAction() {
		// TODO Auto-generated method stub
		
	}

}
