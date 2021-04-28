package documentReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements DocumentReader {

	private String filename;
	public ExcelReader(String filename) {
		this.filename = filename;
	}
	@Override
	public ArrayList<String> read() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			FileInputStream excelFile = new FileInputStream(new File(filename));
            Workbook workbook = new XSSFWorkbook(excelFile);
            excelFile.close();
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == CellType.STRING) {
                    	result.add(currentCell.getStringCellValue());
                        //System.out.print(currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                    	System.out.println("makaronia me tyri");
                    	result.add(currentCell.getNumericCellValue()+"");
                       // System.out.print(currentCell.getNumericCellValue() + "--");
                    }

                }
                result.add("\n");
                //System.out.println();
               
            }
            
            
            workbook.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println(result);
		return result;
	}
}
