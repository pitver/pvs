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

    public Page<ListUnprocessed> findAll(Pageable pageable) {
        return listUnprocessedRepo.findAll(pageable);
    }

    public String coyntAllByAdressLike(String adress) {
        return listUnprocessedRepo.countAllByAdressLike(adress);
    }
    public List<String> distinctAllByAdressLike(String adress) {
        return listUnprocessedRepo.distinctAllByAdressLike(adress);
    }

}
