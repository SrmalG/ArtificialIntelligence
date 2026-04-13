package com.example.demo.dto;

import javax.validation.constraints.NotNull;

public class ArrayCalculations {

    @NotNull
    double[][] inputs;

    public ArrayCalculations() {
    }

    public ArrayCalculations(double[][] inputs) {
        this.inputs = inputs;
    }

    public double[][] getInputs() {
        return inputs;
    }

    public void setInputs(double[][] inputs) {
        this.inputs = inputs;
    }
}
