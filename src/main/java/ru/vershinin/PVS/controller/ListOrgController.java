package ru.vershinin.PVS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vershinin.PVS.model.ListOrg;
import ru.vershinin.PVS.service.ListOrgService;

import java.util.List;

@Controller
public class ListOrgController {

    private final ListOrgService listOrgService;

    public ListOrgController(ListOrgService listOrgService) {
        this.listOrgService = listOrgService;
    }

    @GetMapping("/listorg")
    public String getListOrg(Model model){
        List<ListOrg> listOrg =listOrgService.findAll();

        model.addAttribute("Listorg", listOrg);

        return "/listorg";
    }
}
