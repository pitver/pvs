package ru.vershinin.PVS.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TypeOrg {
    private static final String FILENAME = "D:\\test\\t\\typeOrg.json";

    public static String getType(String typeAbb) {

        Map<String, String> typeOrg = new HashMap<>();

        typeOrg.put("ИП","Индивидуальный предприниматель");
        typeOrg.put("ООО","Общество с ограниченной ответственностью");
        typeOrg.put("ОАО","Открытое акционенрное общество");
        typeOrg.put("ЗАО","Закрытое акционенрное общество");
        typeOrg.put("ПАО","Публичное акционенрное общество");
        typeOrg.put("УК","Управляющая компапния");
        typeOrg.put("НАО","Непубличное акционенрное общество");
        typeOrg.put("ГУП","Государственное унитарное предприятие");
        typeOrg.put("ФГУП","Федеральное государственное унитарное предприятие");
        typeOrg.put("НКО","Некоммерческая организация");
        typeOrg.put("ГБУК","Государственное бюджетное учреждение культуры");
        typeOrg.put("ГБУЗ","Государственное бюджетное учреждение здравоохранения");
        typeOrg.put("ГКУ","Государственное казённое учреждение");
        typeOrg.put("АО","Акционенрное общество");
        typeOrg.put("СК","Строительная компания");


        if(typeOrg.containsKey(typeAbb)){
            String type=typeOrg.get(typeAbb);
            return type;
        }else {
            return "invalid type";
        }
        /*ObjectMapper mapper = new ObjectMapper();
        Map map = new HashMap();
        try {
            map = mapper.readValue(new File(FILENAME),
                    new TypeReference<HashMap<String, Object>>() {
                    });


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }


        if (map.containsKey(typeAbb)) {
            String type = String.valueOf(map.get(typeAbb));
            return type;
        } else {
            return "invalid type";
        }*/


    }


}
