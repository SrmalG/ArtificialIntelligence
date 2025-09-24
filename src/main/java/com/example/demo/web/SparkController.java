package com.example.demo.web;

import com.example.demo.api.SparkApi;
import com.example.demo.service.impl.SparkServiceImpl;
import com.example.demo.utilities.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/spark/v1")
public class SparkController implements SparkApi {

    @Autowired
    private SparkServiceImpl sparkServiceImpl;

    @PostMapping("/upload")
    public ResponseEntity<String> processFile(@RequestParam("file") MultipartFile file) {
        try {
            sparkServiceImpl.preprocessingDriver(file);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }



}
