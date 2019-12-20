package cn.itsource.javaexecle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author myllxy
 * @create 2019-12-18 10:29
 */
public class ExcelTest {
    @Test
    public void readExcel() throws Exception {
        File file = new File("demo.xlsx");
        FileInputStream fis = new FileInputStream(file);
        //1.读取一个Excel文件(内存中)
        Workbook wb = new XSSFWorkbook(fis);
        //2.拿到第个sheet表
        Sheet sheet = wb.getSheetAt(0);
        //3.拿到wb中的行(不要拿头部)
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            //4.拿到每一列(格子)
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                System.out.print(cell.getStringCellValue() + " ");
            }
            System.out.println();
        }
    }
}