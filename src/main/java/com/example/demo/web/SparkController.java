package com.example.demo.web;
import com.example.demo.dto.GenericResponse;
import com.example.demo.service.SparkService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


@RestController
@RequestMapping("/api/spark/v1")
public class SparkController {

    private final SparkService sparkService;

    public SparkController(SparkService sparkService) {
        this.sparkService = sparkService;
    }

    @PostMapping("/preprocess")
    public ResponseEntity<?> processFile(@RequestParam("file") MultipartFile file,
                                         @RequestParam("columns") List<String> columns,
                                         @RequestParam("dateColumn") String dateColumn) {
        try {
            return ResponseEntity.ok(sparkService.preprocessingDriver(file, columns, dateColumn));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new GenericResponse(false, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new GenericResponse(false, e.getMessage()));
        }
    }
}
