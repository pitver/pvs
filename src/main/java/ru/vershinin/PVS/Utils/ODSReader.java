package ru.vershinin.PVS.Utils;

import org.jopendocument.dom.spreadsheet.MutableCell;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ODSReader
 *
 * @author Вершинин Пётр
 */
public class ODSReader {
    private ODSReader() {
    }

    public static List<List<String>> readODSUnprocessed(File file) {
        SpreadSheet spreadsheet;
        List<List<String>> tempList = new ArrayList<>();
        List<String> tempListForRow0 = new ArrayList<>();//фио
        List<String> tempListForRow1 = new ArrayList<>();//адресс регистрации
        List<String> tempListForRow2 = new ArrayList<>();//принимающая сторона
        List<String> tempListForRow3 = new ArrayList<>();//инн принимающей стороны
        List<String> tempListForRow4 = new ArrayList<>();//дата
        List<String> tempListForRow5 = new ArrayList<>();// плательщик
        try {
            //Getting the 0th sheet for manipulation| pass sheet name as string

            spreadsheet = SpreadSheet.createFromFile(file);

            //Get row count and column count
            int nColCount = spreadsheet.getSheet(0).getColumnCount();
            int nRowCount = spreadsheet.getSheet(0).getRowCount();

            //Iterating through each row of the selected sheet
            MutableCell cell = null;
            for (int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++) {
                //Iterating through each column
                for (int nColIndex = 0; nColIndex < nColCount; nColIndex++) {
                    cell = spreadsheet.getSheet(0).getCellAt(nColIndex, nRowIndex);
                    switch (nColIndex) {
                        case 0://фио
                            tempListForRow0.add(String.valueOf(cell.getValue()));
                            // System.out.println((String) cell.getValue());
                            break;
                        case 1://адресс регистрации
                            tempListForRow1.add(String.valueOf(cell.getValue()));
                            break;
                        case 2://принимающая сторона
                            tempListForRow2.add(String.valueOf(cell.getValue()));
                            break;
                        case 3://инн принимающей стороны
                            tempListForRow3.add(String.valueOf(cell.getValue()));
                            break;
                        case 4://дата
                            tempListForRow4.add(String.valueOf(cell.getValue()));
                            break;
                        case 5:// плательщик
                            tempListForRow5.add(String.valueOf(cell.getValue()));
                            break;
                    }

                }

            }
            tempList.add(tempListForRow0);
            tempList.add(tempListForRow1);
            tempList.add(tempListForRow2);
            tempList.add(tempListForRow3);
            tempList.add(tempListForRow4);
            tempList.add(tempListForRow5);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    public static List<List<String>> readODSAllOrg(File file) {
        SpreadSheet spreadsheet;
        List<List<String>> tempList = new ArrayList<>();
        List<String> tempListForRow0 = new ArrayList<>();
        List<String> tempListForRow1 = new ArrayList<>();
        List<String> tempListForRow2 = new ArrayList<>();

        try {
            //Getting the 0th sheet for manipulation| pass sheet name as string
            spreadsheet = SpreadSheet.createFromFile(file);

            //Get row count and column count
            int nColCount = spreadsheet.getSheet(0).getColumnCount();
            int nRowCount = spreadsheet.getSheet(0).getRowCount();

            //Iterating through each row of the selected sheet
            MutableCell cell = null;
            for (int nRowIndex = 0; nRowIndex < nRowCount; nRowIndex++) {
                //Iterating through each column
                for (int nColIndex = 0; nColIndex < nColCount; nColIndex++) {
                    cell = spreadsheet.getSheet(0).getCellAt(nColIndex, nRowIndex);

                    switch (nColIndex) {
                        case 0:
                            tempListForRow0.add(String.valueOf(cell.getValue()));
                            // System.out.println((String) cell.getValue());
                            break;
                        case 1:
                            tempListForRow1.add(String.valueOf(cell.getValue()));
                            break;
                        case 2:
                            tempListForRow2.add(String.valueOf(cell.getValue()));
                            break;
                    }

                }
            }
            tempList.add(tempListForRow0);
            tempList.add(tempListForRow1);
            tempList.add(tempListForRow2);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempList;
    }
}
