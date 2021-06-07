package ru.vershinin.PVS.service;

import org.springframework.stereotype.Service;
import ru.vershinin.PVS.repos.ListUnprocessedRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public void masReg() {
        List<String> listPrepOrg = getListPrepOrg();
        List<List<String>> list = new ArrayList<>();

        for (String nameOrg : listPrepOrg) {


            list.add(Collections.singletonList(nameOrg));
            list.add(listUnprocessedRepo.findMassReOrg(nameOrg));


        }
        list.stream().forEach(System.out::println);

    }
}
