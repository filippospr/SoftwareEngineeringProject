package documentReader;

import java.util.ArrayList;

public abstract class ReaderDecorator implements DocumentReader {
	
	private DocumentReader reader;
	public ReaderDecorator(DocumentReader reader)
	{
		this.reader = reader;
	}
	@Override
	public ArrayList<String> read(){
		ArrayList<String> content=reader.read();
		ArrayList<String> decryptContent=new ArrayList<String>();
		
		for(String word:content){
		    decryptContent.add(decrypt(word));
		}
		return decryptContent;
	}
	
	public abstract String decrypt(String word);
}
