package ru.vershinin.PVS.service;

/**
 * ListUnprocessedService
 *
 * @author Вершинин Пётр
 */

import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ListUnprocessed> findAll() {
        return listUnprocessedRepo.findAll();
    }

    public String coyntAllByAdressLike(String adress) {
        return listUnprocessedRepo.countAllByAdressLike(adress);
    }
    public List<String> distinctAllByAdressLike(String adress) {
        return listUnprocessedRepo.distinctAllByAdressLike(adress);
    }

}
