package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Arrays;

public class TrainModelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private double[][] data;
    @NotBlank
    private double[][] target;

    private int epocs = 100;

    private double learningRate = 0.015;

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public double[][] getTarget() {
        return target;
    }

    public void setTarget(double[][] target) {
        this.target = target;
    }


    public int getEpocs() {
        return epocs;
    }

    public void setEpocs(int epocs) {
        this.epocs = epocs;
    }

    @Override
    public String toString() {
        return "TrainModelDto{" +
                "x=" + Arrays.toString(data) +
                ", y=" + Arrays.toString(target) +
                ", epocs=" + epocs +
                '}';
    }
}
