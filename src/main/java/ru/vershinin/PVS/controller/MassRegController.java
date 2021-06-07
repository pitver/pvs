package ru.vershinin.PVS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vershinin.PVS.service.MasRegProcedureService;

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
    public String getMassReg(){

masRegProcedureService.masReg();
        return "redirect:/listorg";
    }
}
