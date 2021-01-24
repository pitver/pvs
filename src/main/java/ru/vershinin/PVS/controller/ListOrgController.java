package ru.vershinin.PVS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vershinin.PVS.Utils.TypeOrg;
import ru.vershinin.PVS.model.ListOrg;
import ru.vershinin.PVS.service.ListOrgService;
import ru.vershinin.PVS.service.ListUnprocessedService;

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
        // listNameOrg.stream().forEach(System.out::println);


        for (String abb : listNameOrg) {
            String resultBeforeSpace = getAbbrBeforeSpace(abb);
            String resultAfterSpace = getAbbrAfterSpace(abb);
            String adress = resultBeforeSpace.toUpperCase(Locale.ROOT) + resultAfterSpace.toUpperCase(Locale.ROOT);
            // System.out.println(adress);
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
            List<String> regAdressUnique = selectingUniqueNames(regAdress);

            //System.out.println(adress + "--- " + count+ "-- "+regAdress);
            System.out.println(count + "-- " + regAdress);
        }
        return "/listorg";
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
        for (int i = 0; i < nameAdress.size(); i++) {



            if (i == 0) {
                tempList.add(nameAdress.get(i));
            } else {
                    int index=0;
                for (int j = 0; j < tempList.size(); j++) {
                    if (!tempList.get(index).contains(nameAdress.get(i))) {
                        tempList.add(nameAdress.get(i));
                    }
                    index++;
                }
            }
        }

        return tempList;
    }
}
