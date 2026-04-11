package com.example.demo.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;

public class TrainModelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private double[][] data;
    @NotNull
    private double[][] target;

    private int epochs = 100;

    private double learningRate = 0.05;

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


    public int getEpochs() {
        return epochs;
    }

    public void setEpochs(int epochs) {
        this.epochs = epochs;
    }

    @Override
    public String toString() {
        return "TrainModelDto{" +
                "x=" + Arrays.toString(data) +
                ", y=" + Arrays.toString(target) +
                ", epocs=" + epochs +
                '}';
    }
}
