package com.example.demo.dto;

import java.util.ArrayList;

public class CalculateArrayResponseDto {

    private ArrayList<CalculateResponse> responsesArray;
    private ArrayList<Double> predictions;

    public CalculateArrayResponseDto(ArrayList<CalculateResponse> responsesArray, ArrayList<Double> predictions) {
        this.responsesArray = responsesArray;
        this.predictions = predictions;
    }

    public CalculateArrayResponseDto(ArrayList<Double> predictions) {
        this.predictions = predictions;
    }

    public ArrayList<CalculateResponse> getResponsesArray() {
        return responsesArray;
    }

    public void setResponsesArray(ArrayList<CalculateResponse> responsesArray) {
        this.responsesArray = responsesArray;
    }

    public ArrayList<Double> getPredictions() {
        return predictions;
    }

    public void setPredictions(ArrayList<Double> predictions) {
        this.predictions = predictions;
    }
}
