package com.example.demo.entitiesAI;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.utilities.Utilities.calculateCombination;
import static com.example.demo.utilities.Utilities.sigmoidFunction;

public class NeuronLayer {

    private ArrayList<Neuron> neurons;
    private double learningRate;
    private String activationMethod;

    public NeuronLayer() {
        this.neurons = new ArrayList<>();
        this.learningRate = 0.01;
        this.activationMethod = "SIGMOID";
    }

    /**
     * Ejecuta el forward de toda la capa.
     * Toma las entradas, las pasa por cada neurona y devuelve las salidas.
     */
    public double[] forwardLayer(double[] inputs) {
        double[] outputs = new double[neurons.size()];
        for (int i = 0; i < neurons.size(); i++) {
            Neuron n = neurons.get(i);
            outputs[i] = forward(inputs, n.getWeight(), n.getBias(), activationMethod);
        }
        return outputs;
    }

    /**
     * Calcula la salida de una neurona individual.
     */
    public static double forward(final double[] data, final double[] weight, final double bias, final String activationMode) {
        double combination = calculateCombination(data, weight, bias);
        switch (activationMode.toUpperCase()) {
            case "SIGMOID":
                return sigmoidFunction(combination);
            default:
                throw new IllegalArgumentException("Not a valid activation function");
        }
    }

    /**
     * Calcula el error delta (para backpropagation futuro)
     */
    public static double calculateDeltaError(final double target, final double forwardResult) {
        return (forwardResult - target) * forwardResult * (1 - forwardResult);
    }

    // Getters & Setters
    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(ArrayList<Neuron> neurons) {
        this.neurons = neurons;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public String getActivationMethod() {
        return activationMethod;
    }

    public void setActivationMethod(String activationMethod) {
        this.activationMethod = activationMethod;
    }

    public void addNeuron(Neuron neuron) {
        this.neurons.add(neuron);
    }
}
