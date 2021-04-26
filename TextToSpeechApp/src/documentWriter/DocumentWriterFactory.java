package documentWriter;


public class DocumentWriterFactory 
{

	public DocumentWriter createWriter(String filename, String filetype, String encryption)
	{
		DocumentWriter reader = null;
		switch (filetype){
		case "word":
			reader = new WordWriter(filename);
			break;
		case "excel":
			reader = new ExcelWriter(filename);
			break;
		}
		
		switch (filetype){
		case "atbash":
			reader = new WriterAtBashDecorator(reader);
			break;
		case "rot13":
			reader = new WriterRot13Decorator(reader);
			break;
		}
		
		return reader;
	}
	
}
