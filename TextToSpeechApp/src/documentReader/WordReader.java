package documentReader;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WordReader implements DocumentReader {

	private String filename;
	public WordReader(String filename)
	{
		this.filename = filename;
	}
	@Override
	public ArrayList<String> read(){
		ArrayList<String> result = new ArrayList<String>();
        try (XWPFDocument doc = new XWPFDocument(
                Files.newInputStream(Paths.get(filename)))) {

            XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(doc);
            String docText = xwpfWordExtractor.getText();
            result.addAll(Arrays.asList(docText.split("\n")));
            xwpfWordExtractor.close();
            //System.out.println(docText);

            // find number of words in the document
            long count = Arrays.stream(docText.split("\\s+")).count();
            System.out.println("Total words: " + count);

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return result;
	}
	public static void main(String[] args){
		WordReader test = new WordReader("bankai.docx");
		ArrayList<String> testList= test.read();
		String tt = "bankai";
		System.out.println('A'-5);
		for(char i: tt.toCharArray()) {
			System.out.print(i);
		}
			
		System.out.println(testList);
	}
}
