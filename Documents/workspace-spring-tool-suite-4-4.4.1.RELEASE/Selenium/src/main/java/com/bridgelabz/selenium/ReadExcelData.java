package com.bridgelabz.selenium;

public class ReadExcelData {

	public static void main(String[] args) {
		ExcelConfig excel=new ExcelConfig("/home/admin106/Documents/ReadExcel.xlsx");
		System.out.println(excel.getData(0, 0, 0));

	}

}
