package ru.vershinin.PVS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vershinin.PVS.service.MasRegProcedureService;

import java.util.Map;

/**
 * MassRegController
 *
 * @author Вершинин Пётр
 */
@Controller
public class MassRegController {

    private  final MasRegProcedureService masRegProcedureService;

    public MassRegController(MasRegProcedureService masRegProcedureService) {
        this.masRegProcedureService = masRegProcedureService;
    }

    @GetMapping("/masreg")
    public String getMassReg(Model model){

        Map<String, Object> mapRegOrg = masRegProcedureService.masReg();
        model.addAttribute("mapRegOrg",mapRegOrg);
        return "/masreg";
    }
}
