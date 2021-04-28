package commands;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CommandsFactory{

	public EventHandler<ActionEvent> createCommand(String s){
		switch(s) {
	 		case "open":
    			return new OpenDocument();	
		  	case "save":
		    	return new SaveDocument();
		    case "speech":
		    	return new DocumentToSpeech();
			default:
				return new OpenDocument();
		}

	}
}
