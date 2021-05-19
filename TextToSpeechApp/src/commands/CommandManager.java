package commands;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import audioPlayer.AudioPlayerController;
import documentReader.DocumentReaderFactory;
import documentWriter.DocumentWriterFactory;
import model.Document;

public class CommandManager {
	private Document document;
	private AudioPlayerController audioController;
	private int transformStatus = -1;
	private HashMap<String,ArrayList<String>> extensionsList;
	
	public CommandManager(AudioPlayerController controller)
	{
		document = new Document();
		audioController = controller;
		document.setDocReaderFactory(new DocumentReaderFactory());
		document.setDocWriterFactory(new DocumentWriterFactory());
		initExtentionsList();
	}
	
	public String openDocument(File file, String encryption)
	{
		String name = file.getName();
		String ext = findFileType(name).toLowerCase();
		if (ext == null) {
			return null;
		}
		
		document.open(file, ext, encryption);
		
		ArrayList<String> contents = document.getContents();
		StringBuilder res = new StringBuilder();
		for (String s : contents)
		{
		    res.append(s);
		    res.append("\n");
		}		
		return res.toString();
	}
	
	public void saveDocument(File file, String encryption) {
		String ext = findFileType(file.getName()).toLowerCase();
		if (ext == null) {
			return;
		}
		document.save(file, ext, encryption);
	}
	
	public void play(String text) {
		audioController.play(text);
	}
	
	public void setTransformStatus(int status)
	{
		transformStatus = status;
	}
	
	public int getTransformStatus(){
		return transformStatus;
	}
	
	public HashMap<String, ArrayList<String>> getExtentionsList(){
		return extensionsList;
	}
	
	private String findFileType(String name) {
		String ext = null;
		int dotPos = name.lastIndexOf(".");
		if(dotPos > 0) {
			ext = "*"+name.substring(dotPos);
		}
		
		for (String i: extensionsList.keySet()) {
			if (extensionsList.get(i).contains(ext)) {
				return i;
			}
		}
		return null;
	}
	
	private void initExtentionsList(){
		extensionsList = new HashMap<String, ArrayList<String>>();
		ArrayList<String> wordList = new ArrayList<String>();
		wordList.add("*.docx");
		wordList.add("*.doc");
		wordList.add("*.docm");
		extensionsList.put("Word", wordList);
		ArrayList<String> excelList = new ArrayList<String>();
		excelList.add("*.xlsx");
		excelList.add("*.xls");
		extensionsList.put("Excel", excelList);
	}

	public void setContents(String[] text) {
		document.setContents(text);
	}
}
