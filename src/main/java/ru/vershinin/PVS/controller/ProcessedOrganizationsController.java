package ru.vershinin.PVS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vershinin.PVS.service.ActionWithListOrgService;
import ru.vershinin.PVS.model.ListOrg;
import ru.vershinin.PVS.model.ProcessedOrganizations;
import ru.vershinin.PVS.service.ListOrgService;
import ru.vershinin.PVS.service.ProcessedOrganizationsService;

import java.util.List;

/**
 * ProcessedOrganizationsController
 *
 * @author Вершинин Пётр
 */
@Controller

public class ProcessedOrganizationsController {

    private final ActionWithListOrgService actionWithListOrg;
    private final ListOrgService listOrgService;

    private final ProcessedOrganizationsService processedOrganizationsService;

    public ProcessedOrganizationsController(ListOrgService listOrgService, ProcessedOrganizationsService processedOrganizationsService, ActionWithListOrgService actionWithListOrg) {
        this.listOrgService = listOrgService;
        this.processedOrganizationsService = processedOrganizationsService;
        this.actionWithListOrg = actionWithListOrg;
    }

    @GetMapping("/listorgall")
    public String findAll(Model model) {

        List<ProcessedOrganizations> listAllProcessedOrgan = processedOrganizationsService.findAll();
        model.addAttribute("listall", listAllProcessedOrgan);

        return "listorgall";
    }

    @PostMapping("/listorgall")
    public String reCount(Model model,
                          @RequestParam(required = false, defaultValue = "") String reCount) {

        if(reCount!=null){
         List<ListOrg> listOrg = listOrgService.findAll();
        List<String> listNameOrg = listOrgService.findAllNameOrg();
        actionWithListOrg.preparingForRecording(listOrg, listNameOrg);
        }

        return "redirect:/listorgall";
    }
}
