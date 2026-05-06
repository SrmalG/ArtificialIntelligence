package com.example.demo.entitiesAI;

import java.util.Arrays;
import java.util.Random;

public class Neuron {

    private static final Random RNG = new Random(42);

    private double[] weight;
    private double bias;
    private double[] lastInput;
    private double lastOutput;
    private double deltaError;

    public double getDeltaError() {
        return deltaError;
    }

    public void setDeltaError(double deltaError) {
        this.deltaError = deltaError;
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

    public Neuron(final Integer dimension, final double bias) {
        this.weight = new double[dimension];
        this.lastInput = new double[dimension];
        this.bias = bias;

        // Xavier initialization for weights
        double limit = Math.sqrt(6.0 / (dimension + 1));
        for (int i = 0; i < dimension; i++) {
            this.weight[i] = RNG.nextDouble() * 2 * limit - limit;
        }
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

    @Override
    public String toString() {
        return "Neuron{" +
                "weight=" + Arrays.toString(weight) +
                ", bias=" + bias +
                ", lastInput=" + Arrays.toString(lastInput) +
                ", lastOutput=" + lastOutput +
                ", deltaError=" + deltaError +
                '}';
    }
}
