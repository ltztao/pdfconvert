package com.pdf.convert;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ExportExcel {
    public static void main(String[] args) {
        try{
            InputStream is = new FileInputStream("G:\\approvalRecord-01.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(is);
            Sheet sheet = wb.getSheetAt(0);
            int rowNum = 2;
            int size = 3;
            for(int i = rowNum;i<size;i++){
                Row row = sheet.createRow(i);
                for(int j = 0;j < 1;j++){
                    Cell cell = row.createCell(j);
                    cell.setCellValue("2354323468083789828");
                }
            }
            File file = new File("G:\\approvalRecord-01.xlsx");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            wb.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("output successful");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
