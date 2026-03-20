package com.example.demo.web;
import com.example.demo.service.SparkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@RestController
@RequestMapping("/api/spark/v1")
public class SparkController {

    @Autowired
    private SparkService sparkServiceImpl;

    @PostMapping("/preprocess")
    public Object processFile(@RequestParam("file") MultipartFile file,
                              @RequestParam("columns") List<String> columns,
                              @RequestParam("dateColumn") String dateColumn) {
        try {
            return sparkServiceImpl.preprocessingDriver(file, columns, dateColumn);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest();
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }



}
