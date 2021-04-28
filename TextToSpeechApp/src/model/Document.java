package model;

import java.util.ArrayList;

import documentReader.*;
import documentWriter.*;

public class Document {
	private ArrayList<String> contents;
	private DocumentWriter writer;
	private DocumentReader reader;
	private DocumentReaderFactory readerFactory;
	private DocumentWriterFactory writerFactory;
	private TTSFacade audioManager;
	
	public Document()
	{
		contents = new ArrayList<String>();
	}
	
	public void setAudioManager(TTSFacade audioManager)
	{
		this.audioManager = audioManager;
	}
	
	public void setDocReaderFactory(DocumentReaderFactory readerFactory)
	{
		this.readerFactory = readerFactory;
	}
	
	public void setDocWriterFactory(DocumentWriterFactory writerFactory)
	{
		this.writerFactory = writerFactory;
	}
	
	public void open(String name, String filetype, String encryption)
	{
		reader = readerFactory.createReader(name, filetype, encryption);
		contents = reader.read();
	}
	public void save(String name, String filetype, String encryption)
	{
		writer = writerFactory.createWriter(name, filetype, encryption);
		writer.write(contents);
	}
	
	public void playContents()
	{
		for(String line: contents) {
			audioManager.play(line);
		}
	}
	
	public void playLine(int lineNo)
	{
		String line = contents.get(lineNo);
		audioManager.play(line);
	}
	
	public ArrayList<String> getContents()
	{
		return contents;
	}
}
