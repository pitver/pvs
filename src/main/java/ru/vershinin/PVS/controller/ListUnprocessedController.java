package ru.vershinin.PVS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vershinin.PVS.model.ListUnprocessed;
import ru.vershinin.PVS.service.ListUnprocessedService;

/**
 * ListUnprocessedController
 *
 * @author Вершинин Пётр
 */
@Controller
public class ListUnprocessedController {

    @Autowired
    private ListUnprocessedService listUnprocessedService;


    @GetMapping("/reptoaddres")
    public String getListUnprocessed(Model model, @PageableDefault(size = 30) Pageable pageable){

        Page<ListUnprocessed> page=listUnprocessedService.findAll(pageable);
        int[] body;
        if (page.getTotalPages() > 7) {
            int totalPages = page.getTotalPages();
            int pageNumber = page.getNumber()+1;
            int[] head = (pageNumber > 4) ? new int[]{1, -1} : new int[]{1,2,3};
            int[] bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1) ? new int[]{pageNumber-2, pageNumber-1} : new int[]{};
            int[] bodyCenter = (pageNumber > 3 && pageNumber < totalPages - 2) ? new int[]{pageNumber} : new int[]{};
            int[] bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3) ? new int[]{pageNumber+1, pageNumber+2} : new int[]{};
            int[] tail = (pageNumber < totalPages - 3) ? new int[]{-1, totalPages} : new int[] {totalPages-2, totalPages-1, totalPages};
            body = ControllerUtils.merge(head, bodyBefore, bodyCenter, bodyAfter, tail);

        } else {
            body = new int[page.getTotalPages()];
            for (int i = 0; i < page.getTotalPages(); i++) {
                body[i] = 1+i;
            }
        }

        model.addAttribute("page",page);
        model.addAttribute("body", body);
        model.addAttribute("url","reptoaddres");

        return "reptoaddres";
    }
}
