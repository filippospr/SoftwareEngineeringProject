package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import commands.CommandManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SaveDocumentTest {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void saveWordDocumentNoEnryption() {
		
		File wordFile = new File(ClassLoader.getSystemResource("./test_files/").getFile()+"/testword2.docx");
		try {
			wordFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommandManager manager= new CommandManager();
		manager.setContents("Makaronia 62… I  ate some anchovy today.\n".split("\n"));
		manager.saveDocument(wordFile,"");
		File testFile = new File(ClassLoader.getSystemResource("./test_files/testword2.docx").getFile());
		String contents = manager.openDocument(testFile, "");
		assertEquals("Makaronia 62… I  ate some anchovy today.\n", contents);
		testFile.delete();
		
	}
	
	@Test
	void saveExcelDocumentNoEnryption() {
		
		File excelFile = new File(ClassLoader.getSystemResource("./test_files/").getFile()+"/testexcel.docx");
		try {
			excelFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommandManager manager= new CommandManager();
		manager.setContents("Makaronia 62… I  ate some anchovy today.\n".split("\n"));
		manager.saveDocument(excelFile,"");
		File testFile = new File(ClassLoader.getSystemResource("./test_files/testexcel.docx").getFile());
		String contents = manager.openDocument(testFile, "");
		assertEquals("Makaronia 62… I  ate some anchovy today.\n", contents);
		testFile.delete();
		
	}
	
	
	@Test
	void saveWordDocumentAtBashEnryption() {
		
		File wordFile = new File(ClassLoader.getSystemResource("./test_files/").getFile()+"/testword2.docx");
		try {
			wordFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommandManager manager= new CommandManager();
		manager.setContents("Makaronia 62… I  ate some anchovy today.\n".split("\n"));
		manager.saveDocument(wordFile,"atbash");
		File testFile = new File(ClassLoader.getSystemResource("./test_files/testword2.docx").getFile());
		String contents = manager.openDocument(testFile, "atbash");
		assertEquals("Makaronia 62… I  ate some anchovy today.\n", contents);
		testFile.delete();
		
	}
	
	@Test
	void saveWordDocumentRot13Enryption() {
		
		File wordFile = new File(ClassLoader.getSystemResource("./test_files/").getFile()+"/testword2.docx");
		try {
			wordFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommandManager manager= new CommandManager();
		manager.setContents("Makaronia 62… I  ate some anchovy today.\n".split("\n"));
		manager.saveDocument(wordFile,"rot13");
		File testFile = new File(ClassLoader.getSystemResource("./test_files/testword2.docx").getFile());
		String contents = manager.openDocument(testFile, "rot13");
		assertEquals("Makaronia 62… I  ate some anchovy today.\n", contents);
		testFile.delete();
		
	}
	
	@Test
	void saveExcelDocumentAtBashEnryption() {
		
		File excelFile = new File(ClassLoader.getSystemResource("./test_files/").getFile()+"/testexcel.docx");
		try {
			excelFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommandManager manager= new CommandManager();
		manager.setContents("Makaronia 62… I  ate some anchovy today.\n".split("\n"));
		manager.saveDocument(excelFile,"atbash");
		File testFile = new File(ClassLoader.getSystemResource("./test_files/testexcel.docx").getFile());
		String contents = manager.openDocument(testFile, "atbash");
		assertEquals("Makaronia 62… I  ate some anchovy today.\n", contents);
		testFile.delete();
		
	}
	
	@Test
	void saveExcelDocumentRot13Enryption() {
		
		File excelFile = new File(ClassLoader.getSystemResource("./test_files/").getFile()+"/testexcel.docx");
		try {
			excelFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CommandManager manager= new CommandManager();
		manager.setContents("Makaronia 62… I  ate some anchovy today.\n".split("\n"));
		manager.saveDocument(excelFile,"rot13");
		File testFile = new File(ClassLoader.getSystemResource("./test_files/testexcel.docx").getFile());
		String contents = manager.openDocument(testFile, "rot13");
		assertEquals("Makaronia 62… I  ate some anchovy today.\n", contents);
		testFile.delete();
		
	}
	
	

}
