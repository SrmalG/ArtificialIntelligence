package com.example.demo.web;

import com.example.demo.dto.SimpleCalculation;
import com.example.demo.dto.TrainModelDto;
import com.example.demo.service.FNNArtificialEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ai/v1")
public class ArtificialController {

    @Autowired
    private FNNArtificialEngineService model;

    @PostMapping("/train")
    public ResponseEntity<String> processFile(@RequestBody TrainModelDto data) {
        try {
            model.trainFNN(data.getData(), data.getTarget(), data.getEpocs());
            return new ResponseEntity<>("ok on training",HttpStatus.OK);
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

    @PostMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestBody SimpleCalculation data) {
        try {
            double result = model.calculate(data.getInput());
            return new ResponseEntity<>(String.format("The result of the result is: %s", result), HttpStatus.OK);
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
