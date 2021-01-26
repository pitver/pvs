package ru.vershinin.PVS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vershinin.PVS.Utils.TypeOrg;
import ru.vershinin.PVS.Utils.getListOrg;
import ru.vershinin.PVS.model.ListOrg;
import ru.vershinin.PVS.service.ListOrgService;
import ru.vershinin.PVS.service.ListUnprocessedService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class ListOrgController {

    private final ListOrgService listOrgService;

    public ListOrgController(ListOrgService listOrgService, ListUnprocessedService listUnprocessedService) {
        this.listOrgService = listOrgService;
        this.listUnprocessedService = listUnprocessedService;
    }

    private final ListUnprocessedService listUnprocessedService;

    @GetMapping("/listorg")
    public String getListOrg(Model model) {
        List<ListOrg> listOrg = listOrgService.findAll();


        model.addAttribute("Listorg", listOrg);
        List<String> listNameOrg = listOrgService.findAllNameOrg();

        for (int i = 0; i < listOrg.size(); i++) {
            // System.out.println(listOrg.get(i).getNum()+" "+listOrg.get(i).getNameOrg()+" "+listOrg.get(i).getInn());

            String num = listOrg.get(i).getNum();
            String nameOrg=listOrg.get(i).getNameOrg();
            String inn=listOrg.get(i).getInn();
            String resultBeforeSpace = getAbbrBeforeSpace(listNameOrg.get(i));
            String resultAfterSpace = getAbbrAfterSpace(listNameOrg.get(i));
            String adress = resultBeforeSpace.toUpperCase(Locale.ROOT) + resultAfterSpace.toUpperCase(Locale.ROOT);

            try {
                getCountAndRegAdress(resultAfterSpace, adress,num,nameOrg,inn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "/listorg";
    }

    private void getCountAndRegAdress(String resultAfterSpace, String adress, String num, String nameOrg, String inn) throws SQLException {
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
        //  List<String> regAdressUnique = selectingUniqueNames(regAdress);

        //System.out.println(adress + "--- " + count+ "-- "+regAdress);
        //System.out.println(num + "-- " + nameOrg + "-- " + count + "-- " +inn + "-- " +  regAdress);
       // getListOrg.writeToDBProcessOrganization(num, nameOrg,count,inn,regAdress);
    }

    private String getAbbrBeforeSpace(String abb) {
        String resultBeforeSpace = null;
        String str;
        int end = abb.indexOf(' '); // ищем индекс первого пробела
        if (end != -1) { // проверяем найдено ли (-1 = не найдено)
            resultBeforeSpace = abb.substring(0, end);
        }

        str = TypeOrg.getType(resultBeforeSpace);
        return str;
    }

    private String getAbbrAfterSpace(String abb) {
        String resultAfterSpace = null;
        int end = abb.indexOf(' '); // ищем индекс первого пробела
        if (end != -1) { // проверяем найдено ли (-1 = не найдено)
            resultAfterSpace = abb.substring(end);
        }
        return resultAfterSpace;
    }

    private List<String> selectingUniqueNames(List<String> nameAdress) {

        List<String> tempList = new ArrayList<>();

        System.out.println(nameAdress.size());
        List<String> result = new ArrayList<>(nameAdress);
        for (int i = 0; i < nameAdress.size(); i++) {


            if (i == 0) {
                tempList.add(nameAdress.get(i));
                result.remove(0);
            } else {
                int index = 0;
                for (int j = 1; j < result.size(); j++) {

                    if (tempList.get(index).contains(result.get(i))) {
                        result.remove(j);
                        break;
                    } else {
                        tempList.add(result.get(i));
                    }
                    index++;
                }
            }
        }

        return tempList;
    }
}
