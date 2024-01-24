package pl.seleniumdemo.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static void readExcelFile(String fileName) throws IOException {
        // czytanie plików tylko znajdujących się w resources
        File file = new File("src/test/resources/" + fileName);
        FileInputStream inputStream = new FileInputStream(file);

        // implementacja zależna od rozszerzenia pliku (dodane później)
        Workbook workbook = null;
        // wyciąganie z dostarczonego stringa kropki i to co dalej (czyli rozszerzenie)
        String fileExt = fileName.substring(fileName.indexOf("."));
        // wprowadzenie zależności dla implementacji workbooka
        if (fileExt.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExt.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        // pobranie pierwszego arkusza z pliku excel
        Sheet sheet = workbook.getSheetAt(0);
        // sprawdzenie ile wierszy mamy w pliku excel
        int rowCount = sheet.getLastRowNum();

        // pomijamy pierwszy wiersz (z nagłówkami)
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            // pobranie wartości z pierwszej kolumny
            System.out.println(row.getCell(0).getStringCellValue());
            // pobranie wartości z drugiej kolumny
            System.out.println(row.getCell(1).getStringCellValue());
        }
    }

    public static void main(String[] args) throws IOException {
        readExcelFile("testData.xlsx");
    }
}
