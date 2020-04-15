package com.automation.tests.excel;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteIntroExcelFile {

    @Test
    public void writeIntroFileTest() throws IOException {

        FileInputStream inputStream = new FileInputStream("VytrackTestUsers.xlsx");
        Workbook workbook = WorkbookFactory.create(inputStream);
        inputStream.close();

        Sheet sheet = workbook.getSheet("QA3-short");
        Row row = sheet.getRow(1);//2nd row
        Cell cell = row.getCell(row.getLastCellNum() - 1);//last column

        System.out.println("Before: "+cell.getStringCellValue());
        cell.setCellValue("FAILED");//I am changing from n/a to passed
        System.out.println("After: "+cell.getStringCellValue());

        Row firstRow = sheet.getRow(0);//get first row
        Cell newCell = firstRow.createCell(row.getLastCellNum());//create new cell
        newCell.setCellValue("Date of execution");//give the name to this cell

        //I crete if I want to write something into the file
        //don't forget to close excel file if you opened it
        FileOutputStream outputStream = new FileOutputStream("VytrackTestUsers.xlsx");
        workbook.write(outputStream);
        workbook.close();



    }
}
