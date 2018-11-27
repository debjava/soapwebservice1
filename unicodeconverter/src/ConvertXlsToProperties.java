import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class ConvertXlsToProperties
{

	public static String getCustomCellValue( HSSFRow row , int cellIndex )
	{
		String cellValue = null;
		HSSFCell cell = row.getCell(cellIndex);
		if( cell != null )
		{
			String cellData = cell.getStringCellValue();
			System.out.println("Cell Value--->"+cellData);
			if( cellData.indexOf("=") != -1 )
			{
				cellValue = cellData;
			}
		}
		return cellValue;
	}

	public static Map<String,Properties> getCellInfo( String filePath , int cellIndex ) throws Exception
	{
		Map<String,Properties> enRuMap = new LinkedHashMap<String,Properties>();
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
		HSSFWorkbook workbook = new HSSFWorkbook(fs);

		int totalSheets = workbook.getNumberOfSheets();
//		System.out.println("Total Sheets :::"+totalSheets);
		for( int i = 0 ; i < totalSheets ; i++ )
		{
			HSSFSheet sheet = workbook.getSheetAt(i);
			String sheetName = sheet.getSheetName();

//			System.out.println("Sheet Name :::"+sheetName);
//			System.out.println("-----------------------------------");
			int totalRowsNo = sheet.getPhysicalNumberOfRows();
//			System.out.println("Total Row Number :::"+totalRowsNo);
			//All Rows in a Sheet
			Properties enRuProp = new Properties();
			for( int j = 0 ; j < totalRowsNo ; j++ )
			{
				HSSFRow row = sheet.getRow(j);

				if( row != null )
				{
					String cellValue = getCustomCellValue(row, cellIndex);
					if( cellValue != null )
					{
						try
						{
							String[] tempStr = cellValue.split("[=]");
							String englishKey = tempStr[0];
							String russianValue = tempStr[1];
							enRuProp.put(englishKey, russianValue);
						}
						catch( IndexOutOfBoundsException ibe )
						{
							enRuProp.put("", "");
						}
					}
				}
			}
			//Add the Properties to a list
			enRuMap.put(sheetName,enRuProp);
		}
		return enRuMap;
	}

	public static void main(String[] args) throws Exception
	{
		String filePath = "data/ru/Translation GD RUS new_Prop_Done.xls";
		String cellName = "PropertyFile";
		Map<String,Properties> enRuMap = getCellInfo(filePath, 5);
	}

}
