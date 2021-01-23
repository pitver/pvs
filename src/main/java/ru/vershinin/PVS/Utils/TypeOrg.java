package ru.vershinin.PVS.Utils;

import java.util.HashMap;
import java.util.Map;

public class TypeOrg {

    public static String getType(String typeAbb) {
        Map<String, String> typeOrg = new HashMap<>();

        typeOrg.put("ИП","Индивидуальный предприниматель");
        typeOrg.put("ООО","Общество с ограниченной ответсвенность");
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

       if(typeOrg.containsKey(typeAbb)){
           String type=typeOrg.get(typeAbb);
           return type;
       }else {
           return null;
       }

    }


}
