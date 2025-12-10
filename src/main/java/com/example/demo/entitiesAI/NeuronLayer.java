package com.example.demo.entitiesAI;

import com.example.demo.utilities.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

    public NeuronLayer(final double learningRate) {
        this.neurons = new ArrayList<>();
        this.learningRate = learningRate;
        this.activationMethod = "SIGMOID";
    }

    public NeuronLayer(final double learningRate, final String activationMethod) {
        this.neurons = new ArrayList<>();
        this.learningRate = learningRate;
        this.activationMethod = activationMethod;
    }


    /**
     * Ejecuta el forward de toda la capa en paralelo.
     */
    public double[] forwardLayer(final double[] inputs) {
        final int size = neurons.size();
        final double[] outputs = new double[size];

        IntStream.range(0, size).parallel().forEach(i -> {
            final Neuron n = neurons.get(i);
            final double linealCombination = calculateCombination(inputs, n.getWeight(), n.getBias());
            outputs[i] = forward(linealCombination, activationMethod);
            n.setLastOutput(outputs[i]);
            n.setLastCombinationCalculation(linealCombination);
            n.setLastInput(inputs);
        });

        return outputs;
    }

    /**
     * Calcula la salida de una neurona individual.
     */
    public double forward(final double combination, final String activationMode) {
        switch (activationMode.toUpperCase()) {
            case "SIGMOID":
                return sigmoidFunction(combination);
            default:
                throw new IllegalArgumentException("Not a valid activation function");
        }
    }

    public void calculateDeltasFromNextLayer(final NeuronLayer nextLayer) {
        for (int j = 0; j < neurons.size(); j++) {
            Neuron current = neurons.get(j);
            double sum = 0.0;
            for (int k = 0; k < nextLayer.neurons.size(); k++) {
                Neuron next = nextLayer.neurons.get(k);
                sum += next.getDeltaError() * next.getWeight()[j];
            }
            double out = current.getLastOutput();
            double derivative = out * (1 - out);
            current.setDeltaError(sum * derivative);
        }
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
