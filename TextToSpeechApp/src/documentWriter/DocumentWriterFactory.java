package documentWriter;

import java.io.File;

public class DocumentWriterFactory 
{

	public DocumentWriter createWriter(File file, String filetype, String encryption)
	{
		DocumentWriter writer = null;
		switch (filetype){
		case "word":
			writer = new WordWriter(file);
			break;
		case "excel":
			writer = new ExcelWriter(file);
			break;
		}
		
		switch (encryption){
		case "atbash":
			writer = new WriterAtBashDecorator(writer);
			break;
		case "rot13":
			writer = new WriterRot13Decorator(writer);
			break;
		}
		
		return writer;
	}
	
}
