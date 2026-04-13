package com.example.demo.dto;

public class CalculateResponseArray {

    private CalculateResponse[] calculateResponses;

    public CalculateResponseArray(CalculateResponse[] calculateResponses) {
        this.calculateResponses = calculateResponses;
    }

    public CalculateResponse[] getCalculateResponses() {
        return calculateResponses;
    }

    public void setCalculateResponses(CalculateResponse[] calculateResponses) {
        this.calculateResponses = calculateResponses;
    }
}
