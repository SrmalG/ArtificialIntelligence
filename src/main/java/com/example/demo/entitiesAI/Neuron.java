package com.example.demo.entitiesAI;

import java.util.List;

public class Neuron {

    private double[] weight;
    private double bias;

    public Neuron() {}

    public Neuron(final Integer dimension) {
        this.weight = new double[dimension];
        this.bias = 0.5;
    }

    public Neuron(final Integer dimension, final double bias) {
        this.weight = new double[dimension];
        this.bias = bias;
    }

    public double[] getWeight() {
        return this.weight;
    }

    public void setWeight(final double[] weight) {
        this.weight = weight;
    }

    public double getBias() {
        return this.bias;
    }

    public void setBias(final double bias) {
        this.bias = bias;
    }

}
