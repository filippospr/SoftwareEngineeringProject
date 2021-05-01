package model;

import java.io.File;
import java.util.ArrayList;

import documentReader.*;
import documentWriter.*;

public class Document {
	private ArrayList<String> contents;
	private DocumentWriter writer;
	private DocumentReader reader;
	private DocumentReaderFactory readerFactory;
	private DocumentWriterFactory writerFactory;
	
	public Document()
	{
		contents = new ArrayList<String>();
	}
	
	public void setDocReaderFactory(DocumentReaderFactory readerFactory)
	{
		this.readerFactory = readerFactory;
	}
	
	public void setDocWriterFactory(DocumentWriterFactory writerFactory)
	{
		this.writerFactory = writerFactory;
	}
	
	public void open(File file, String filetype, String encryption)
	{
		reader = readerFactory.createReader(file, filetype, encryption);
		contents = reader.read();
	}
	public void save(String name, String filetype, String encryption)
	{
		writer = writerFactory.createWriter(name, filetype, encryption);
		writer.write(contents);
	}
	
	public ArrayList<String> getContents()
	{
		return contents;
	}
}
