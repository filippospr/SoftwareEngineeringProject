package documentWriter;

import java.io.File;

public class DocumentWriterFactory 
{

	public DocumentWriter createWriter(File file, String filetype, String encryption)
	{
		DocumentWriter reader = null;
		switch (filetype){
		case "word":
			reader = new WordWriter(file);
			break;
		case "excel":
			reader = new ExcelWriter(file);
			break;
		}
		
		switch (encryption){
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
