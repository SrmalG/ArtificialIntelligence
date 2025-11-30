package com.example.demo.entitiesAI;

public class Neuron {

    private double[] weight;
    private double bias;
    private double[] lastInput;
    private double lastOutput;
    private double lastCombinationCalculation;

    public double getLastCombinationCalculation() {
        return lastCombinationCalculation;
    }

    public void setLastCombinationCalculation(double lastCombinationCalculation) {
        this.lastCombinationCalculation = lastCombinationCalculation;
    }

    public double[] getLastInput() {
        return lastInput;
    }

    public void setLastInput(double[] lastInput) {
        this.lastInput = lastInput;
    }

    public double getLastOutput() {
        return lastOutput;
    }

    public void setLastOutput(double lastOutput) {
        this.lastOutput = lastOutput;
    }

    public Neuron() {}

    public Neuron(final Integer dimension) {
        this.weight = new double[dimension];
        this.bias = 0.5;
    }

    public Neuron(final Integer dimension, final double bias) {
        this.weight = new double[dimension];
        this.bias = bias;
        this.lastInput = new double[dimension];
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
