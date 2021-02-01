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

        ObjectMapper mapper = new ObjectMapper();
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
        }


    }


}
