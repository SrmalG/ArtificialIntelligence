package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MetricsInDto {

    @NotEmpty(message = "Predictions array cannot be empty")
    private double[] predictions;

    @NotEmpty(message = "Targets array cannot be empty")
    private double[] targets;

    @NotNull(message = "Threshold is required")
    private double threshold; // Changed to Object wrapper to allow null-checking

    public MetricsInDto() {
    }

    public MetricsInDto(double[] predictions, double[] targets, double threshold) {
        this.predictions = predictions;
        this.targets = targets;
        this.threshold = threshold;
    }

    public double[] getPredictions() {
        return predictions;
    }

    public void setPredictions(double[] predictions) {
        this.predictions = predictions;
    }

    public double[] getTargets() {
        return targets;
    }

    public void setTargets(double[] targets) {
        this.targets = targets;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
}