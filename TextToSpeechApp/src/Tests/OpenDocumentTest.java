package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import commands.CommandManager;

import org.junit.jupiter.api.Test;

class OpenDocumentTest {

	@Test
	void openWordDocumentNoEnryption() {
		File wordFile= new File(ClassLoader.getSystemResource("./test_files/testword1.docx").getFile());
		CommandManager manager= new CommandManager();
		String contents=manager.openDocument(wordFile,"");
		assertEquals("Makaronia 62… I  ate some anchovy today.\n", contents);		
	}
	
	@Test
	void openExcelDocumentNoEnryption() {
		File excelFile= new File(ClassLoader.getSystemResource("test_files/testexcel.xlsx").getFile());
		CommandManager manager= new CommandManager();
		String contents=manager.openDocument(excelFile,"");
		assertEquals("This is  a  test \n", contents);
	}
	
	@Test
	void openWordDocumentAtBashEnryption() {
		File wordFile= new File(ClassLoader.getSystemResource("./test_files/testwordatbash.docx").getFile());
		CommandManager manager= new CommandManager();
		String contents=manager.openDocument(wordFile,"atbash");
		assertEquals("Makaronia 62… I  ate some anchovy today.\n", contents);		
	}
	
	@Test
	void openExcelDocumentAtBashEnryption() {
		File excelFile= new File(ClassLoader.getSystemResource("test_files/testexcelatbash.xlsx").getFile());
		CommandManager manager= new CommandManager();
		String contents=manager.openDocument(excelFile,"atbash");
		assertEquals("This is a test \n", contents);		
		
	}
	
	@Test
	void openWordDocumentRot13Enryption() {
		File wordFile= new File(ClassLoader.getSystemResource("./test_files/testwordrot13.docx").getFile());
		CommandManager manager= new CommandManager();
		String contents=manager.openDocument(wordFile,"rot13");
		assertEquals("Makaronia 62… I  ate some anchovy today.\n", contents);		
		
	}
	
	
	@Test
	void openExcelDocumentRot13Enryption() {
		File excelFile= new File(ClassLoader.getSystemResource("test_files/testexcelrot13.xlsx").getFile());
		CommandManager manager= new CommandManager();
		String contents=manager.openDocument(excelFile,"rot13");
		assertEquals("This is a test \n", contents);		
		
	}
}
