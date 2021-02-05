package ru.vershinin.PVS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.vershinin.PVS.Utils.UtilsFiles;
import ru.vershinin.PVS.model.ListOrg;
import ru.vershinin.PVS.service.ListOrgService;

import java.io.IOException;
import java.util.List;

@Controller
public class ListOrgController {
    public final String path = "D:\\test\\t\\Список организаций.ods";

    private final ListOrgService listOrgService;

    public ListOrgController(ListOrgService listOrgService) {
        this.listOrgService = listOrgService;
    }


    @GetMapping("/listorg")
    public String getListOrg(Model model) {
        List<ListOrg> listOrg = listOrgService.findAll();
        model.addAttribute("Listorg", listOrg);

        return "/listorg";
    }

    @PostMapping("/listorg")
    public String getListOrg(@RequestParam("file") MultipartFile file) throws IOException {
        //очищаем таблицу
        listOrgService.deleteAll();

        UtilsFiles.checkingFileExist(file, path,1);
        // записываем в таблицу list_org


        return "redirect:/listorg";
    }
}
