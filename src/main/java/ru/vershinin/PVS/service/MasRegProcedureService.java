package ru.vershinin.PVS.service;

import org.springframework.stereotype.Service;
import ru.vershinin.PVS.repos.ListUnprocessedRepo;

import java.util.List;

@Service
public class MasRegProcedureService {

    private final ListUnprocessedRepo listUnprocessedRepo;

    public MasRegProcedureService(ListUnprocessedRepo listUnprocessedRepo) {
        this.listUnprocessedRepo = listUnprocessedRepo;
    }

    public List<String> getListPrepOrg(){
        List<String> listPreparedOrganizations = listUnprocessedRepo.findListPreparedOrganizations();
        return listPreparedOrganizations;
    }

    public void masReg(){
        List<String> listPrepOrg = getListPrepOrg();
        for (String tempListOrg:listPrepOrg) {

        }
    }
}
