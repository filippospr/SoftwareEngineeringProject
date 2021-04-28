package commands;

import model.Document;

public class Kaposmelene {
	Document document;
	
	public Kaposmelene()
	{
		document = new Document();
	}
	
	public String openDocument(String path, String filetype, String encryption)
	{
		document.open(path, filetype, encryption);
		return document.toString();
	}
}
