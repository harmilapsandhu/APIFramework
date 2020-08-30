package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {
	
	@SuppressWarnings("deprecation")
	public ArrayList<String> getData(String testcaseName, String sheetName) throws IOException
	{
		ArrayList<String> a = new ArrayList<String>();
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Harmilap\\Documents\\Work\\Auba.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook spreadsheet = new XSSFWorkbook(fis);
		int TotalSheets = spreadsheet.getNumberOfSheets();
		
		for (int i=0; i<TotalSheets; i++)
		{
			if(spreadsheet.getSheetName(i).equalsIgnoreCase(sheetName))
			{
				XSSFSheet RequiredSheet = spreadsheet.getSheetAt(i);
				
				Iterator<Row> Rowing = RequiredSheet.iterator();
				Row FirstRow = Rowing.next();
				Iterator<Cell> Celling = FirstRow.cellIterator();
				int j=0;
				int column=0;
				while(Celling.hasNext())
				{
					Cell NewValue = Celling.next();
					if(NewValue.getStringCellValue().equalsIgnoreCase("Genre"))
					{
						column=j;
					}
					j++;
				}
				System.out.println(column);
				while(Rowing.hasNext())
				{
					Row RowA = Rowing.next();
					if(RowA.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
					{
						Iterator<Cell> Blue = RowA.cellIterator();
						while(Blue.hasNext())
						{
							Cell c = Blue.next();
							if(c.getCellTypeEnum()==CellType.STRING)   //Use this if-else to get values for both strings and integers
							{
							a.add(c.getStringCellValue()); //Added it to Array created at top
							}
							else{  //Use this condition so that your code can return a Numeric Value
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
								
							}
							
								
							
						}
					}
					
				}
				
			}
		}
		
		return a;
		
	}
	


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		


}}