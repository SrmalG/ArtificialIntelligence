package com.example.demo.entitiesAI;

import java.util.List;

public class Neuron {

    private Integer neuronId;
    private double[] data;
    private double[] weight;
    private double bias;
    private List<Integer> connectedTo;

    public List<Integer> getConnectedTo() {
        return connectedTo;
    }

    public void setConnectedTo(List<Integer> connectedTo) {
        this.connectedTo = connectedTo;
    }

    public Neuron() {}

    public Neuron(final Integer dimension) {
        this.data = new double[dimension];
        this.weight = new double[dimension];
        this.bias = 0.5;
    }

    public Integer getNeuronId() {
        return neuronId;
    }

    public void setNeuronId(Integer neuronId) {
        this.neuronId = neuronId;
    }

    public Neuron(final Integer dimension, final double bias) {
        this.data = new double[dimension];
        this.weight = new double[dimension];
        this.bias = bias;
    }

    public double[] getData() {
        return this.data;
    }

    public double[] getWeight() {
        return this.weight;
    }

    public double getBias() {
        return this.bias;
    }

    public void setData(final double[] data) {
        this.data = data;
    }

    public void setWeight(final double[] weight) {
        this.weight = weight;
    }

    public void setBias(final double bias) {
        this.bias = bias;
    }

}