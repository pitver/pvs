package ru.vershinin.PVS.Utils;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * UtilsFiles
 *
 * @author Вершинин Пётр
 */
public class UtilsFiles {
    public static void checkingFileExist(@RequestParam("file") MultipartFile file, String path,int type) throws IOException {
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
            file.transferTo(new File(path));
        }
        getListOrg.writeDBOfType(type);
    }
}
