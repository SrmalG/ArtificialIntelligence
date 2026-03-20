package com.example.demo.dto;

import java.io.Serializable;
import java.util.Arrays;

public class TrainModelDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private double[][] data;
    private double[][] target;
    private int epocs;

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
