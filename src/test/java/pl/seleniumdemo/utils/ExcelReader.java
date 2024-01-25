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

    public static Object[][] readExcelFile(String fileName) throws IOException {
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
        // pobranie pierwszego wiersza i sprawdzenie ile kolumn mamy w pliku
        int columnCount = sheet.getRow(0).getLastCellNum();
        // odczyt danych do tablicy dwuwymiarowej o rozmiarach max. il. wierzy x max. il. kolumn
        Object[][] data = new Object[rowCount][columnCount];

        // pomijamy pierwszy wiersz (z nagłówkami)
        for (int i = 1; i <= rowCount; i++) {
            Row row = sheet.getRow(i);

            for(int j=0; j < columnCount; j++){
                data[i-1][j] = row.getCell(j).getStringCellValue();
            }

        }

        return data;
    }

    public static void main(String[] args) throws IOException {
        readExcelFile("testData.xlsx");
    }
}
