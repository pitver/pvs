package ru.vershinin.PVS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vershinin.PVS.Utils.TypeOrg;
import ru.vershinin.PVS.Utils.getListOrg;
import ru.vershinin.PVS.model.ListOrg;
import ru.vershinin.PVS.service.ListUnprocessedService;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
@Service
public class ActionWithListOrgService {

    private final ListUnprocessedService listUnprocessedService;

    public ActionWithListOrgService(ListUnprocessedService listUnprocessedService) {
        this.listUnprocessedService = listUnprocessedService;
    }

    private  String getCount(String resultAfterSpace, String adress) {
        String count;
        count = listUnprocessedService.countAllByAdressLike(adress);
        if (count.equals("0")) {
            count = listUnprocessedService.countAllByAdressLike(resultAfterSpace.toUpperCase(Locale.ROOT));
            if (count.equals("0")) {
                count = listUnprocessedService.countAllByAdressLike(resultAfterSpace.toUpperCase(Locale.ROOT).substring(1, 6));
            }
        }
        return count;
    }

    private  List<String> getRegAdress(String resultAfterSpace, String adress) {
        List<String> regAdress;
        regAdress = listUnprocessedService.distinctAllByAdressLike(adress);
        if (regAdress.isEmpty()) {
            regAdress = listUnprocessedService.distinctAllByAdressLike(resultAfterSpace.toUpperCase(Locale.ROOT));
            if (regAdress.isEmpty()) {
                regAdress = listUnprocessedService.distinctAllByAdressLike(resultAfterSpace.toUpperCase(Locale.ROOT).substring(1, 6));
            }
        }
        return regAdress;
    }

    private  String getAbbrBeforeSpace(String abb) {
        String resultBeforeSpace = null;
        String str;
        int end = abb.indexOf(' '); // ищем индекс первого пробела
        if (end != -1) { // проверяем найдено ли (-1 = не найдено)
            resultBeforeSpace = abb.substring(0, end);
        }

        str = TypeOrg.getType(resultBeforeSpace);
        return str;
    }

    private  String getAbbrAfterSpace(String abb) {
        String resultAfterSpace = null;
        int end = abb.indexOf(' '); // ищем индекс первого пробела
        if (end != -1) { // проверяем найдено ли (-1 = не найдено)
            resultAfterSpace = abb.substring(end);
        }
        return resultAfterSpace;
    }

    public void preparingForRecording(List<ListOrg> listOrg, List<String> listNameOrg) {
        for (int i = 0; i < listOrg.size(); i++) {

            String num = listOrg.get(i).getNum();
            String nameOrg= listOrg.get(i).getNameOrg();
            String inn= listOrg.get(i).getInn();
            String resultBeforeSpace = getAbbrBeforeSpace(listNameOrg.get(i));
            String resultAfterSpace = getAbbrAfterSpace(listNameOrg.get(i));
            String adress = resultBeforeSpace.toUpperCase(Locale.ROOT) + resultAfterSpace.toUpperCase(Locale.ROOT);

            try {
                String count = getCount(resultAfterSpace, adress);
                List<String> regAdress= getRegAdress(resultAfterSpace, adress);
                getListOrg.writeToDBProcessOrganization(num, nameOrg,count,inn,regAdress);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
