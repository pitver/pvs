package ru.vershinin.PVS.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.vershinin.PVS.Utils.UtilsFiles;
import ru.vershinin.PVS.Utils.getListOrg;
import ru.vershinin.PVS.model.ListUnprocessed;
import ru.vershinin.PVS.service.ListOrgService;
import ru.vershinin.PVS.service.ListUnprocessedService;

import java.io.File;
import java.io.IOException;

/**
 * ListUnprocessedController
 *
 * @author Вершинин Пётр
 */
@Controller
public class ListUnprocessedController {
    public final String path = "D:\\test\\t\\Отчёт по адресам.ods";


    private final ListOrgService listOrgService;

    private final ListUnprocessedService listUnprocessedService;

    public ListUnprocessedController(ListUnprocessedService listUnprocessedService, ListOrgService listOrgService) {
        this.listUnprocessedService = listUnprocessedService;
        this.listOrgService = listOrgService;
    }


    @GetMapping("/reptoaddres")
    public String getListUnprocessed(Model model, @PageableDefault(size = 30) Pageable pageable,
                                     @RequestParam(required = false, defaultValue = "") String filter) {

        Page<ListUnprocessed> page;
        String count = null;
       /* List<String> nameOrg=listOrgService.findAllNameOrg();
        model.addAttribute("nameOrg",nameOrg);*/

        if (filter != null && !filter.equals("")) {
            page = listUnprocessedService.findByReceivingPartyContains(filter.toUpperCase(), pageable);
            count = listUnprocessedService.countAllByAdressLike(filter.toUpperCase());
            model.addAttribute("url", "?filter=" + filter + "&");
        } else {
            page = listUnprocessedService.findAll(pageable);
            model.addAttribute("url", "reptoaddres?");
        }

        int[] body;
        if (page.getTotalPages() > 7) {
            int totalPages = page.getTotalPages();
            int pageNumber = page.getNumber() + 1;
            int[] head = (pageNumber > 4) ? new int[]{1, -1} : new int[]{1, 2, 3};
            int[] bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1) ? new int[]{pageNumber - 2, pageNumber - 1} : new int[]{};
            int[] bodyCenter = (pageNumber > 3 && pageNumber < totalPages - 2) ? new int[]{pageNumber} : new int[]{};
            int[] bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3) ? new int[]{pageNumber + 1, pageNumber + 2} : new int[]{};
            int[] tail = (pageNumber < totalPages - 3) ? new int[]{-1, totalPages} : new int[]{totalPages - 2, totalPages - 1, totalPages};
            body = ControllerUtils.merge(head, bodyBefore, bodyCenter, bodyAfter, tail);

        } else {
            body = new int[page.getTotalPages()];
            for (int i = 0; i < page.getTotalPages(); i++) {
                body[i] = 1 + i;
            }
        }

        model.addAttribute("page", page);
        model.addAttribute("count", count);
        model.addAttribute("body", body);


        return "reptoaddres";
    }

    @PostMapping("/reptoaddres")
    public String getReptoaddres(Pageable pageable,
                                 @RequestParam("file") MultipartFile file) throws IOException {
        //очищаем таблицу
        listUnprocessedService.deleteAll();

        UtilsFiles.checkingFileExist(file, path,2);

        return "redirect:/reptoaddres";
    }


}
