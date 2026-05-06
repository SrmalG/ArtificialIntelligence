package com.example.demo.dto;

public class CalculateResponse {

    private boolean success;
    private String message;
    private double result;
    private double[] input;

    public CalculateResponse(boolean success, String message, double result, double[] input) {
        this.success = success;
        this.message = message;
        this.result = result;
        this.input = input;
    }

    public double[] getInput() {
        return input;
    }

    public void setInput(double[] input) {
        this.input = input;
    }

    public CalculateResponse(boolean success, String message, double result) {
        this.success = success;
        this.message = message;
        this.result = result;
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

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
