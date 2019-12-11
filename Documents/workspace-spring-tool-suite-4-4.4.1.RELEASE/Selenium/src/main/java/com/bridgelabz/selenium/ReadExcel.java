package com.bridgelabz.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static void main(String[] args) throws IOException {
		
		File file =new File("/home/admin106/Documents/ReadExcel.xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
        XSSFSheet sheet1=workbook.getSheetAt(0);
        int rowcount=sheet1.getLastRowNum();
        for(int i=0;i<rowcount;i++)
        {
        	String data0=sheet1.getRow(i).getCell(0).getStringCellValue();
        	String data1=sheet1.getRow(i).getCell(1).getStringCellValue();
        	System.out.println("Data From Excel :" +data0);
        	System.out.println("Data From Excel :" +data1);
        }
        
        
        
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//        String data0=sheet1.getRow(0).getCell(0).getStringCellValue();
//        System.out.println("Data From Excel :" +data0);
//        String data1=sheet1.getRow(0).getCell(1).getStringCellValue();
//        System.out.println("Data From Excel :" +data1); 	
       
        		
	}

}
