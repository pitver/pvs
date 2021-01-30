package ru.vershinin.PVS.service;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void deleteAll(){listOrgRepo.deleteAll();}
    public List<String>findAllNameOrg(){return listOrgRepo.findAllNameOrg();}







}
