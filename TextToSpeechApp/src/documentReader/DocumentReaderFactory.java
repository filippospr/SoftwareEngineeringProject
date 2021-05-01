package documentReader;

import java.io.File;

public class DocumentReaderFactory 
{

	public DocumentReader createReader(File file, String filetype, String encryption)
	{
		DocumentReader reader = null;
		switch (filetype){
		case "word":
			reader = new WordReader(file);
			break;
		case "excel":
			reader = new ExcelReader(file);
			break;
		}
		
		switch (encryption){
		case "atbash":
			reader = new ReaderAtBashDecorator(reader);
			break;
		case "rot13":
			reader = new ReaderRot13Decorator(reader);
			break;
		}
		
		return reader;
	}
	
}
