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

    @Autowired
    private ListUnprocessedRepo listUnprocessedRepo;

    public  List<ListUnprocessed> findAll(){return listUnprocessedRepo.findAll();}
}
