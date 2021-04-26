package documentWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordWriter implements DocumentWriter {

	private String filename;
	public WordWriter(String filename) {
		this.filename = filename;
	}
	@Override
	public void write(ArrayList<String> contents) {
         try (XWPFDocument doc = new XWPFDocument()) {
            // create a paragraph
            XWPFParagraph p1 = doc.createParagraph();
            p1.setAlignment(ParagraphAlignment.CENTER);

            // set font
            XWPFRun r1 = p1.createRun();
            r1.setItalic(true);
            r1.setFontSize(22);
            r1.setFontFamily("New Roman");
            
            String output="";
            for(String s: contents){
                output+=s;
            }


            r1.setText(output);

            // save it to .docx file
            FileOutputStream out = new FileOutputStream(filename);
            doc.write(out);
         } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	}
    public static void main(String[] args)
    {
    	ArrayList<String> giannhs = new ArrayList<String>();
    	giannhs.add("filokolhs");
    	giannhs.add("dfdsfsdsfsfdsfs");
    	WordWriter w = new WordWriter("minipo.docx");
    	w.write(giannhs);
    }
}
