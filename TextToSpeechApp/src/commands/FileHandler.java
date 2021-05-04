package commands;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileHandler {
	
	private static FileChooser setUpFileChooser(HashMap<String, ArrayList<String>> extensions, String title){
		FileChooser fileChooser = new FileChooser();
		ArrayList<String> combinedExt = new ArrayList<String>();
		for (String i: extensions.keySet()) {
			combinedExt.addAll(extensions.get(i));
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(i+" documents", extensions.get(i)));
		}
		
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All supported documents", combinedExt);
		fileChooser.getExtensionFilters().add(extFilter);
		fileChooser.setSelectedExtensionFilter(extFilter);
		fileChooser.setTitle(title);
		return fileChooser;
	}
	
	public static File showSaveFileChooser(HashMap<String, ArrayList<String>> extensions, Stage mainStage) {
		FileChooser fileChooser = setUpFileChooser(extensions, "Choose File to Load");
		File file = fileChooser.showSaveDialog(mainStage);
		return file;
	}
	
	public static File showLoadFileChooser(HashMap<String, ArrayList<String>> extensions, Stage mainStage) {
		FileChooser fileChooser = setUpFileChooser(extensions, "Choose File to Load");
		File file = fileChooser.showOpenDialog(mainStage);
		return file;
	}
}
