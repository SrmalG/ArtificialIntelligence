package com.example.demo.entitiesAI;

public class Neuron {
    private double[] data;
    private double[] weight;
    private double bias;
    private String activationMethod;
    private double target;

    public Neuron() {}

    public Neuron(final Integer dimension, String activationMethod) {
        this.data = new double[dimension];
        this.weight = new double[dimension];
        this.activationMethod = activationMethod;
        this.bias = 0.5;
    }

    public Neuron(final Integer dimension, final double bias, String activationMethod) {
        this.data = new double[dimension];
        this.weight = new double[dimension];
        this.bias = bias;
        this.activationMethod = activationMethod;
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

    public void setActivactionMethod(final String activationMethod){
        this.activationMethod = activationMethod;
    }
    public double getTarget() {
        return this.target;
    }

    public String getActivationMethod(){
        return this.activationMethod;
    }

    public void setTarget(final double target) {
        this.target = target;
    }

}