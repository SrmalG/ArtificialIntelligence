package com.example.demo.dto;

import javax.validation.constraints.NotBlank;

public class MetricsInDto {

    @NotBlank
    double[] predictions;

    @NotBlank
    double[] targets;

    @NotBlank
    double threshold;

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
