package commands;


import javafx.stage.Stage;

public class CommandsFactory{
	private CommandManager manager;
	private Stage stage;
	
	public CommandsFactory(CommandManager m, Stage s) {
		manager = m;
		stage = s;
	}
	public Action createCommand(String s){
		Action result;
		switch(s) {
	 		case "open":
	 			result = new OpenDocument(stage, manager);
	 			((OpenDocument)result).setEncryption("");
    			return result;
	 		case "openAtBash":
	 			result = new OpenDocument(stage, manager);
	 			((OpenDocument)result).setEncryption("atbash");
    			return result;
	 		case "openRot13":
	 			result = new OpenDocument(stage, manager);
	 			((OpenDocument)result).setEncryption("rot13");
    			return result;
		  	case "save":
		    	return new SaveDocument();
		    case "speech":
		    	return new DocumentToSpeech(stage, manager);
		    default:
		    	return null;
		}

	}
}
