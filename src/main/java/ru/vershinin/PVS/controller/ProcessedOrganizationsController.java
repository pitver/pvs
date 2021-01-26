package ru.vershinin.PVS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vershinin.PVS.model.ProcessedOrganizations;
import ru.vershinin.PVS.service.ProcessedOrganizationsService;

import java.util.List;

/**
 * ProcessedOrganizationsController
 *
 * @author Вершинин Пётр
 */
@Controller
public class ProcessedOrganizationsController {

    private final ProcessedOrganizationsService processedOrganizationsService;

    public ProcessedOrganizationsController(ProcessedOrganizationsService processedOrganizationsService) {
        this.processedOrganizationsService = processedOrganizationsService;
    }

    @GetMapping("/listorgall")
    public String findAll(Model model) {

        List<ProcessedOrganizations> listAllProcessedOrgan = processedOrganizationsService.findAll();
        model.addAttribute("listall",listAllProcessedOrgan);

        return "listorgall";
    }
}
