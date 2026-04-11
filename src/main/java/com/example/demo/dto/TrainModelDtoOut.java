package com.example.demo.dto;

import java.util.ArrayList;

public class TrainModelDtoOut {

    private boolean success;
    private String message;
    private ArrayList<Double> losses;

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
