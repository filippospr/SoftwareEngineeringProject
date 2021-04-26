package documentWriter;

import java.util.ArrayList;

public abstract class WriterDecorator implements DocumentWriter {
	
	DocumentWriter writer;
	
	public WriterDecorator(DocumentWriter writer)
	{
		this.writer = writer;
	}
	@Override
	public void write(ArrayList<String> contents) {
		ArrayList<String> result = new ArrayList<String>();
		for(String s: contents)
		{
			result.add(encrypt(s));
		}
		writer.write(result);
	}
	public abstract String encrypt(String text);

}
