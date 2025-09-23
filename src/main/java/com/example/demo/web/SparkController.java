package com.example.demo.web;

import com.example.demo.api.SparkApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/spark/v1")
public class SparkController implements SparkApi {

    private static final String UPLOAD_DIR = "/Users/gorge213/Desktop/TFG/int/src/main/resources/filesUploaded";


    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "El archivo está vacío.";
        }
        try {
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            File destinationFile = new File(uploadDir, file.getOriginalFilename());
            file.transferTo(destinationFile);

            return "Archivo subido correctamente: " + destinationFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir el archivo: " + e.getMessage();
        }
    }

}
