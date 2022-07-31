package ru.vershinin.PVS.service;

import org.springframework.stereotype.Service;
import ru.vershinin.PVS.repos.ListUnprocessedRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.lang3.*;

@Service
public class MasRegProcedureService {
    @PersistenceContext
    EntityManager em;


    private final ListUnprocessedRepo listUnprocessedRepo;

    public MasRegProcedureService(ListUnprocessedRepo listUnprocessedRepo) {
        this.listUnprocessedRepo = listUnprocessedRepo;
    }

    public List<String> getListPrepOrg() {
        List<String> listPreparedOrganizations = listUnprocessedRepo.findListPreparedOrganizations();
        return listPreparedOrganizations;
    }

    public Map<String, Object> masReg() {

        Map<String, String> results = new HashMap<>();
        String sql = "select distinct inn, receiving_party from public.\"list_unprocessed\"";
        List<Object[]> result = em
                .createNativeQuery(sql)
                .getResultList();

        List<String> listPrepOrg = new ArrayList<>();
        for (Object[] obj : result) {
            String str = (String) obj[0];
            if (StringUtils.isNotBlank(str)) {
                results.put((String) obj[0], (String) obj[1]);
            } else {
                listPrepOrg.add((String) obj[1]);
            }

        }

        Map<String, Object> map = new HashMap<>();



        results.forEach((key, value) -> {
            if (map.containsKey(value)) {
                map.put(value + " другая оганизация", listUnprocessedRepo.findMassReOrg(value, key));
            } else if (map.containsKey(value + " другая оганизация")) {
                map.put(value + " 1", listUnprocessedRepo.findMassReOrg(value, key));
            } else if (map.containsKey(value + " другая оганизация 1")) {
                map.put(value.replace("1", "2"), listUnprocessedRepo.findMassReOrg(value, key));
            } else if (map.containsKey(value + " другая оганизация 2")) {
                map.put(value.replace("2", "3"), listUnprocessedRepo.findMassReOrg(value, key));
            } else {
                map.put(value, listUnprocessedRepo.findMassReOrg(value, key));
            }
        });



        Map<String, Object> finalMap = map;
        listPrepOrg.parallelStream().forEach(s -> finalMap.put(s, listUnprocessedRepo.findMassReOrg(s)));

        return finalMap;
    }
}
