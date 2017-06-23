package com.zingcrm.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExportLead {

	
	@Autowired CalendarTimeZone timezone;
    public ByteArrayInputStream getExportLead(Map<Integer, Object[]> currentdata, String strName) throws IOException 
    {
        //Blank workbook
        HSSFWorkbook workbook = new HSSFWorkbook(); 
        Calendar c = null;
        int rownum=0;
        
        
        try{
       
        //Create a blank sheet
        HSSFSheet sheet = workbook.createSheet(strName);
        HSSFDataFormat df = workbook.createDataFormat();
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
                "yyyy-MM-dd"));
        
       /* CellStyle cs = workbook.createCellStyle();
        cs.setDataFormat(df.getFormat("yyyy-MM-dd"));*/
        rownum = 0;
      //Iterate over data and write to sheet
        Set<Integer> keyset = currentdata.keySet();
        for (Integer key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = currentdata.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
                else if(obj instanceof Double)
                	cell.setCellValue((Double)obj);
                else if(obj instanceof Date)
                	if(obj!="Date")
                	{
	                	/*c = Calendar.getInstance();
	               		String s=timezone.getDateToString((Date) obj);
	           			c.set(Integer.parseInt(s.split("-")[0]),(Integer.parseInt(s.split("-")[1])-1),Integer.parseInt(s.split("-")[2])+1);*/
	           			cell.setCellValue((Date) obj);
	           			cell.setCellStyle(cellStyle);
                	}
                	else
                		
                	{
                		cell.setCellValue((Date) obj);
                		//cell.setCellStyle(cellStyle);
                	}
	            }
        }
    
        }   
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        workbook.write(baos);
        return new ByteArrayInputStream(baos.toByteArray());

    }
}




