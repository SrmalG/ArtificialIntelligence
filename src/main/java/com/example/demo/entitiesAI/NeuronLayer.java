package com.example.demo.entitiesAI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static com.example.demo.utilities.Utilities.calculateCombination;
import static com.example.demo.utilities.Utilities.sigmoidFunction;

public class NeuronLayer {

    private ArrayList<Neuron> neurons;
    private double learningRate;

    public NeuronLayer(final double learningRate) {
        this.neurons = new ArrayList<>();
        this.learningRate = learningRate;
    }

    /**
     * Ejecuta el forward de toda la capa en paralelo.
     */
    public double[] forwardLayer(final double[] inputs) {
        final int size = neurons.size();
        final double[] outputs = new double[size];

        IntStream.range(0, size).forEach(i -> {
            final Neuron n = neurons.get(i);
            final double linealCombination = calculateCombination(inputs, n.getWeight(), n.getBias());
            outputs[i] = sigmoidFunction(linealCombination);
            n.setLastOutput(outputs[i]);
            n.setLastInput(inputs);
        });

        return outputs;
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

    /**
     * Applies gradient descent update for all neurons in this layer.
     * Expects that lastInput and deltaError have been computed.
     */
    public void applyGradients() {
        for (Neuron n : neurons) {
            final double[] w = n.getWeight();
            final double[] x = n.getLastInput();
            if (w == null || x == null) {
                throw new IllegalStateException("Cannot apply gradients without running forward pass first");
            }
            if (w.length != x.length) {
                throw new IllegalStateException("Weight vector length must match lastInput length");
            }

            for (int i = 0; i < w.length; i++) {
                w[i] = w[i] - learningRate * n.getDeltaError() * x[i];
            }
            n.setWeight(w);
            n.setBias(n.getBias() - learningRate * n.getDeltaError());
        }
    }

    // Getters & Setters
    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void addNeuron(Neuron neuron) {
        this.neurons.add(neuron);
    }
}
