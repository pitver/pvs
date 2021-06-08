package ru.vershinin.PVS.service;

import org.springframework.stereotype.Service;
import ru.vershinin.PVS.repos.ListUnprocessedRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

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
        List<String> listPrepOrg = getListPrepOrg();
        Map<String, Object> map = new HashMap();
        int k=0;

        for (int i = 0; i < listPrepOrg.size(); i++) {

            map.put(String.valueOf(listPrepOrg.get(i)), listUnprocessedRepo.findMassReOrg(String.valueOf(listPrepOrg.get(i))));
            System.out.println(k++);
        }

       map.entrySet().stream().forEach(System.out::println);

        return map;
    }
}
