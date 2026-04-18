package com.example.demo.dto;

import javax.validation.constraints.NotNull;

public class ArrayCalculations {

    @NotNull
    double[][] inputs;

    boolean showInfo;

    public boolean isShowInfo() {
        return showInfo;
    }

    public void setShowInfo(boolean showInfo) {
        this.showInfo = showInfo;
    }

    public ArrayCalculations(@NotNull double[][] inputs, boolean showInfo) {
        this.inputs = inputs;
        this.showInfo = showInfo;
    }

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
