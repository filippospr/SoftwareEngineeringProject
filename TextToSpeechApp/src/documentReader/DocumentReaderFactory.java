package documentReader;

public class DocumentReaderFactory 
{

	public DocumentReader createReader(String filename, String filetype, String encryption)
	{
		DocumentReader reader = null;
		switch (filetype){
		case "word":
			reader = new WordReader(filename);
			break;
		case "excel":
			reader = new ExcelReader(filename);
			break;
		}
		
		switch (filetype){
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
