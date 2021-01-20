package ru.vershinin.PVS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vershinin.PVS.model.ListUnprocessed;
import ru.vershinin.PVS.service.ListUnprocessedService;

import java.util.List;

/**
 * ListUnprocessedController
 *
 * @author Вершинин Пётр
 */
@Controller
public class ListUnprocessedController {

    @Autowired
    private ListUnprocessedService listUnprocessedService;


    @GetMapping("/listorg")
    public String getListUnprocessed(Model model){

        List<ListUnprocessed> listUnprocesseds=listUnprocessedService.findAll();

        model.addAttribute("Listunp",listUnprocesseds);

        return "listorg";
    }
}
