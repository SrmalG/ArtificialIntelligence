package com.example.demo.dto;

public class MetricsOutDto {

    double[][] confusionMatrix;

    double accuracy;

    double f1Score;

    double balancedAccuracy;

    public MetricsOutDto(double[][] confusionMatrix, double accuracy, double f1Score, double balancedAccuracy) {
        this.confusionMatrix = confusionMatrix;
        this.accuracy = accuracy;
        this.f1Score = f1Score;
        this.balancedAccuracy = balancedAccuracy;
    }

    public MetricsOutDto() {}

    public double[][] getConfusionMatrix() {
        return confusionMatrix;
    }

    public void setConfusionMatrix(double[][] confusionMatrix) {
        this.confusionMatrix = confusionMatrix;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public double getF1Score() {
        return f1Score;
    }

    public void setF1Score(double f1Score) {
        this.f1Score = f1Score;
    }

    public double getBalancedAccuracy() {
        return balancedAccuracy;
    }

    public void setBalancedAccuracy(double balancedAccuracy) {
        this.balancedAccuracy = balancedAccuracy;
    }
}
