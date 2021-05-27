package documentReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader implements DocumentReader {

	private File file;
	public ExcelReader(File file) {
		this.file = file;
	}
	
	@Override
	public ArrayList<String> read() {
		ArrayList<String> result = new ArrayList<String>();
		try {
            Workbook workbook = new XSSFWorkbook(file);//create new workbook from the file
            
            //right now we only get the first sheet of the Excel file
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            //get all cells of sheet
            while (iterator.hasNext()) {
            	
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == CellType.STRING) {
                    	result.add(currentCell.getStringCellValue()+" ");
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                    	result.add(currentCell.getNumericCellValue()+" ");
                    }
                }
                result.add("\n");               
            }
            workbook.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return result;
	}
}
