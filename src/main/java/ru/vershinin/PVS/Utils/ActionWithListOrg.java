package ru.vershinin.PVS.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import ru.vershinin.PVS.model.ListOrg;
import ru.vershinin.PVS.service.ListUnprocessedService;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class ActionWithListOrg {

    @Autowired
    private static ListUnprocessedService listUnprocessedService;

    private static void getCountAndRegAdress(String resultAfterSpace, String adress, String num, String nameOrg, String inn) throws SQLException {
        String count = null;
        List<String> regAdress = null;
        count = listUnprocessedService.coyntAllByAdressLike(adress);
        if (count.equals("0")) {
            count = listUnprocessedService.coyntAllByAdressLike(resultAfterSpace.toUpperCase(Locale.ROOT));
            if (count.equals("0")) {
                count = listUnprocessedService.coyntAllByAdressLike(resultAfterSpace.toUpperCase(Locale.ROOT).substring(1, 6));
            }
        }
        regAdress = listUnprocessedService.distinctAllByAdressLike(adress);
        if (regAdress.isEmpty()) {
            regAdress = listUnprocessedService.distinctAllByAdressLike(resultAfterSpace.toUpperCase(Locale.ROOT));
            if (regAdress.isEmpty()) {
                regAdress = listUnprocessedService.distinctAllByAdressLike(resultAfterSpace.toUpperCase(Locale.ROOT).substring(1, 6));
            }
        }

         getListOrg.writeToDBProcessOrganization(num, nameOrg,count,inn,regAdress);
    }

    private static String getAbbrBeforeSpace(String abb) {
        String resultBeforeSpace = null;
        String str;
        int end = abb.indexOf(' '); // ищем индекс первого пробела
        if (end != -1) { // проверяем найдено ли (-1 = не найдено)
            resultBeforeSpace = abb.substring(0, end);
        }

        str = TypeOrg.getType(resultBeforeSpace);
        return str;
    }

    private static String getAbbrAfterSpace(String abb) {
        String resultAfterSpace = null;
        int end = abb.indexOf(' '); // ищем индекс первого пробела
        if (end != -1) { // проверяем найдено ли (-1 = не найдено)
            resultAfterSpace = abb.substring(end);
        }
        return resultAfterSpace;
    }

    public static void preparingForRecording(List<ListOrg> listOrg, List<String> listNameOrg) {
        for (int i = 0; i < listOrg.size(); i++) {

            String num = listOrg.get(i).getNum();
            String nameOrg= listOrg.get(i).getNameOrg();
            String inn= listOrg.get(i).getInn();
            String resultBeforeSpace = getAbbrBeforeSpace(listNameOrg.get(i));
            String resultAfterSpace = getAbbrAfterSpace(listNameOrg.get(i));
            String adress = resultBeforeSpace.toUpperCase(Locale.ROOT) + resultAfterSpace.toUpperCase(Locale.ROOT);

            try {
               getCountAndRegAdress(resultAfterSpace, adress,num,nameOrg,inn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
