package com.example.demo.web;

import com.example.demo.dto.*;
import com.example.demo.service.FNNArtificialEngineService;
import com.example.demo.utilities.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/ai")
public class ArtificialController {

    private static final Logger log = LoggerFactory.getLogger(ArtificialController.class);

    @Value("${app.version}")
    private String version;

    private static final long START_TIME = System.currentTimeMillis();
    private final FNNArtificialEngineService model;

    public ArtificialController(FNNArtificialEngineService model) {
        this.model = model;
    }

    @PostMapping("/train")
    public ResponseEntity<TrainModelDtoOut> processFile(@Valid @RequestBody final TrainModelDto data) {
        try {
            ArrayList<Double> losses = model.trainFNN(data.getData(), data.getTarget(), data.getEpochs(), data.getLearningRate(), data.getHiddenLayers());
            log.info("Training completed – final loss: {}", losses.get(losses.size() - 1));
            if(data.isLossesAvailable())
                return ResponseEntity.ok().body(new TrainModelDtoOut(true, "Train completed", losses, losses.get(losses.size() - 1)));
            else {
                return ResponseEntity.ok().body(new TrainModelDtoOut(true, "Train completed", new ArrayList<>(),losses.get(losses.size() - 1)));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new TrainModelDtoOut(false,e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new TrainModelDtoOut(false,e.getMessage()));
        }
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@Valid @RequestBody final SimpleCalculation data) {
        try {
            double result = model.calculate(data.getInput());
            return ResponseEntity.ok().body(new CalculateResponse(true, String.format("The result is: %s", result), Utilities.obtainResult(result)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new GenericResponse(false,e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new GenericResponse(false,e.getMessage()));
        }
    }

    @PostMapping("/calculateArray")
    public ResponseEntity<?> calculateArray(@Valid @RequestBody final ArrayCalculations data) {
        try {

            final ArrayList<CalculateResponse> calculateResponseArrays = model.calculateArray(data.getInputs());
            return ResponseEntity.ok().body(calculateResponseArrays);

        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new GenericResponse(false,e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(new GenericResponse(false,e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<HealthDto> health() {

        long uptimeMillis = System.currentTimeMillis() - START_TIME;
        long uptimeSeconds = uptimeMillis / 1000;

        final HealthDto response = new HealthDto(
                "UP",
                uptimeSeconds + "s",
                version
        );

        return ResponseEntity.ok(response);
    }
}
