package documentWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter implements DocumentWriter {

	private File file;
	public ExcelWriter(File file) {
		this.file = file;
	}
	@Override	
    public void write(ArrayList<String> contents) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Excel Sheet");

        ArrayList<ArrayList<String>> output=new ArrayList<ArrayList<String>>();
        ArrayList<String> Rrow= new ArrayList<String>();
        for(String s:contents){
            if(s.equals("\n")){
                output.add(Rrow);
                Rrow=new ArrayList<String>();
            }else {
            	Rrow.add(s);
            }
        }
        output.add(Rrow);
        int rowNum = 0;
        for (ArrayList<String> r : output) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (String s : r) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(s);
            }
        }
        
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}
