package documentReader;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordReader implements DocumentReader {

	private File file;
	public WordReader(File file)
	{
		this.file = file;
	}
	@Override
	public ArrayList<String> read(){
		ArrayList<String> result = new ArrayList<String>();
        try (XWPFDocument doc = new XWPFDocument(new FileInputStream(file))) {
        	
            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            
            result.addAll(Arrays.asList(docText.split("\n")));
            xwpfWordExtractor.close();

            // find number of words in the document
            //long count = Arrays.stream(docText.split("\\s+")).count();
            //System.out.println("Total words: " + count);

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result;
	}
}
