package ru.vershinin.PVS.Utils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.vershinin.PVS.model.ListOrg;
import ru.vershinin.PVS.service.ListOrgService;

import java.io.File;
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

        // TODO: 30.01.2021  добавить загрузку файла и если произошла загрузка вызвать все что ниже
        /*List<String> listNameOrg = listOrgService.findAllNameOrg();
        ActionWithListOrg.preparingForRecording(listOrg, listNameOrg);*/
        return "/listorg";
    }

    @PostMapping("/listorg")
    public String getListOrg(@RequestParam("file") MultipartFile file) throws IOException {

        listOrgService.deleteAll();

        try {
            File file1 = new File(file.getOriginalFilename());
            if (file1.delete()) {

            } else {

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDirRas = new File(path);
            if (!uploadDirRas.exists()) {
                uploadDirRas.mkdir();
            }
            file.transferTo(new File(path ));
        }
        getListOrg.writeDBOfType(1);
        return "redirect:/listorg";
    }
}
