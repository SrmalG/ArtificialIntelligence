package com.example.demo.entitiesAI;

public class Neuron {
    private Double[] data;
    private Double[] weight;
    private Double bias;

    public Neuron() {}

    public Neuron(final Integer dimension) {
        this.data = new Double[dimension];
        this.weight = new Double[dimension];
        this.bias = 0.5;
    }

    public Neuron(final Integer dimension, final Double bias) {
        this.data = new Double[dimension];
        this.weight = new Double[dimension];
        this.bias = bias;
    }

    public Double[] getData() {
        return this.data;
    }

    public Double[] getWeight() {
        return this.weight;
    }

    public Double getBias() {
        return this.bias;
    }

    public void setData(final Double[] data) {
        this.data = data;
    }

    public void setWeight(final Double[] weight) {
        this.weight = weight;
    }

    public void setBias(final Double bias) {
        this.bias = bias;
    }

}