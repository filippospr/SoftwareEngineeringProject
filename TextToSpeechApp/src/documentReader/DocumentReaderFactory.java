package documentReader;

public class DocumentReaderFactory 
{

	public DocumentReader createReader(String filename, String filetype, String encryption)
	{
		DocumentReader reader;
		switch (filetype){
		case "word":
			reader = new WordReader(filename);
			break;
		case "excel":
			reader = new ExcelReader(filename);
			break;
		}
	}
	
}
