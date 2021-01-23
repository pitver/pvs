package ru.vershinin.PVS.service;

import org.springframework.stereotype.Service;
import ru.vershinin.PVS.model.ListOrg;
import ru.vershinin.PVS.repos.ListOrgRepo;

import java.util.List;

@Service
public class ListOrgService {
    private final ListOrgRepo listOrgRepo;

    public ListOrgService(ListOrgRepo listOrgRepo) {
        this.listOrgRepo = listOrgRepo;
    }


    public List<ListOrg> findAll(){return listOrgRepo.findAll();}

    public ListOrgRepo findAllByNameOrg(String nameOrg){return listOrgRepo.findAllByNameOrg(nameOrg);}
    public ListOrgRepo findAllByNum(String num){return listOrgRepo.findAllByNum(num);}
    public ListOrgRepo findAllByInn(String inn){return listOrgRepo.findAllByInn(inn);}


}
