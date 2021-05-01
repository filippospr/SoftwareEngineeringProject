package commands;

import javafx.scene.control.TextArea;

public interface Action {
	public void handle();
	public void replayAction();
	public void setReplayManager(ReplayManager manager);
	public void setTextArea(TextArea textArea);
	public Action clone();
	public String toString();
}
