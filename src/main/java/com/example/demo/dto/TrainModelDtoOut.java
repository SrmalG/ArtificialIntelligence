package com.example.demo.dto;

import java.util.ArrayList;

public class TrainModelDtoOut {

    private boolean success;
    private String message;
    private ArrayList<Double> losses;
    private double lastLoss;
    private String timeToTrain;

    public double getLastLoss() {
        return lastLoss;
    }

    public void setLastLoss(double lastLoss) {
        this.lastLoss = lastLoss;
    }

    public TrainModelDtoOut() {}

    public String getTimeToTrain() {
        return timeToTrain;
    }

    public void setTimeToTrain(String timeToTrain) {
        this.timeToTrain = timeToTrain;
    }

    public TrainModelDtoOut(boolean success, String message, ArrayList<Double> losses, double lastLoss, String timeToTrain) {
        this.success = success;
        this.message = message;
        this.losses = losses;
        this.lastLoss = lastLoss;
        this.timeToTrain = timeToTrain;
    }

    public TrainModelDtoOut(boolean success, String message, final ArrayList<Double> losses, final double lastLoss) {
        this.success = success;
        this.message = message;
        this.lastLoss = lastLoss;
        this.losses = losses;
    }

    public TrainModelDtoOut(boolean success, String message, ArrayList<Double> losses) {
        this.success = success;
        this.message = message;
        this.losses = losses;
    }

    public ArrayList<Double> getLosses() {
        return losses;
    }

    public void setLosses(ArrayList<Double> losses) {
        this.losses = losses;
    }

    public TrainModelDtoOut(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
