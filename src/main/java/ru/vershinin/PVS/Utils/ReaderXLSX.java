package ru.vershinin.PVS.Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ReaderXLSX
 *
 * @author Вершинин Пётр
 */
public class ReaderXLSX {

    private static XSSFWorkbook getWorkBookXSSF(String path) throws IOException {
        try (InputStream inputStream = new FileInputStream(path);
             XSSFWorkbook workBook = new XSSFWorkbook(inputStream)) {
            return workBook;
        }
    }

    public  static List<String> getData(String path) throws IOException {

        Sheet sheet = getWorkBookXSSF(path).getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        List<String> listIpAdress = new ArrayList<>();

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.cellIterator();


            while (cells.hasNext()) {

                Cell cell = cells.next();
                CellType cellType = cell.getCellTypeEnum();

                //перебираем возможные типы ячеек
                if (cellType == CellType.STRING) {


                        listIpAdress.add(cell.getStringCellValue());

                }
            }
        }
        return listIpAdress;
    }
}
