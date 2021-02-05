package ru.vershinin.PVS.service;

/**
 * ListUnprocessedService
 *
 * @author Вершинин Пётр
 */

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.vershinin.PVS.model.ListUnprocessed;
import ru.vershinin.PVS.repos.ListUnprocessedRepo;


import java.util.List;

@Service
public class ListUnprocessedService {

    private final ListUnprocessedRepo listUnprocessedRepo;

    public ListUnprocessedService(ListUnprocessedRepo listUnprocessedRepo) {
        this.listUnprocessedRepo = listUnprocessedRepo;
    }

    public void deleteAll(){
        listUnprocessedRepo.deleteAll();
    }

    public Page<ListUnprocessed> findByReceivingPartyContains(String name,Pageable pageable){
        return listUnprocessedRepo.findByReceivingParty(name,pageable);}

    public Page<ListUnprocessed> findAll(Pageable pageable) {
        return listUnprocessedRepo.findAll(pageable);
    }

    public String countAllByAdressLike(String adress) {
        return listUnprocessedRepo.countAllByAdressLike(adress);
    }
    public List<String> distinctAllByAdressLike(String adress) {
        return listUnprocessedRepo.distinctAllByAdressLike(adress);
    }

}
