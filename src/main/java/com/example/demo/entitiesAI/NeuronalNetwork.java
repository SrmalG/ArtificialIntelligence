package com.example.demo.entitiesAI;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NeuronalNetwork {

    private static final Logger log = LoggerFactory.getLogger(NeuronalNetwork.class);

    private static final NeuronalNetwork instance = new NeuronalNetwork();
    private final ArrayList<NeuronLayer> neuronalNetwork;

    private NeuronalNetwork() {
        this.neuronalNetwork = new ArrayList<>();
    }

    public static NeuronalNetwork getInstance() {
        return instance;
    }
    public void addLayer(final NeuronLayer layer) {
        neuronalNetwork.add(layer);
    }

    /**
     * Clears all layers. Useful for tests and for rebuilding a model.
     */
    public void clear() {
        neuronalNetwork.clear();
    }

    /**
     * Ejecuta un forward completo de la red, capa por capa.
     * Cada capa toma la salida de la anterior como entrada.
     */
    public double forward(double[] inputs) {
        double[] output = inputs;
        for (NeuronLayer layer : neuronalNetwork) {
            output = layer.forwardLayer(output);
        }
        return output[0];
    }

    /**
     * Forward pass returning the full last-layer output vector.
     */
    public double[] forwardVector(double[] inputs) {
        double[] output = inputs;
        for (NeuronLayer layer : neuronalNetwork) {
            output = layer.forwardLayer(output);
        }
        return output;
    }

    /**
     * Trains the network with simple stochastic gradient descent.
     *
     * Contract:
     * - inputs: shape [nSamples][nFeatures]
     * - targets: shape [nSamples][nOutputs] (must match output layer size)
     */
    public ArrayList<Double> train(double[][] inputs, double[][] targets, int epochs) {
        if (inputs == null || targets == null) {
            throw new IllegalArgumentException("inputs and targets must not be null");
        }
        if (inputs.length != targets.length) {
            throw new IllegalArgumentException("inputs and targets must have the same number of samples");
        }
        if (neuronalNetwork.isEmpty()) {
            throw new IllegalStateException("Network has no layers");
        }
        if (epochs < 1) {
            throw new IllegalArgumentException("epochs must be >= 1");
        }

        final ArrayList<Double> epochLosses = new ArrayList<>(epochs);

        for (int epoch = 0; epoch < epochs; epoch++) {
            double totalLoss = 0.0;

            for (int i = 0; i < inputs.length; i++) {
                // Forward pass
                double[] output = forwardVector(inputs[i]);

                //MSE error
                double sampleLoss = 0.0;
                for (int j = 0; j < output.length; j++) {
                    double error = output[j] - targets[i][j];
                    sampleLoss += error * error;
                }
                sampleLoss /= output.length;
                totalLoss += sampleLoss;

                // Backpropagation
                putDeltas(targets[i]);
                for (NeuronLayer layer : neuronalNetwork) {
                    layer.applyGradients();
                }
            }

            // Loss promedio de toda la época
            double averageLoss = totalLoss / inputs.length;
            epochLosses.add(averageLoss);

            // Progress log each 10%
            int step = Math.max(1, epochs / 10);
            if (epoch % step == 0 || epoch == epochs - 1) {
                int pct = (int) ((epoch + 1) * 100.0 / epochs);
                log.info("Training {}% ({}/{}) – loss: {}", pct, epoch + 1, epochs, String.format("%.6e", averageLoss));
            }

        }

        return epochLosses;
    }

    /**
     * Calcula los deltas de toda la red.
     *
     * @param target array con los valores esperados de la última capa
     */
    public void putDeltas(double[] target) {
        if (neuronalNetwork.isEmpty()) {
            return;
        }
        if (target == null) {
            throw new IllegalArgumentException("target must not be null");
        }

        NeuronLayer outputLayer = neuronalNetwork.get(neuronalNetwork.size() - 1);

        for (int i = 0; i < outputLayer.getNeurons().size(); i++) {
            final Neuron n = outputLayer.getNeurons().get(i);
            double y = n.getLastOutput();
            double t = target[i];
            double delta = (y - t) * y * (1 - y);
            n.setDeltaError(delta);
        }

        for (int i = neuronalNetwork.size() - 2; i >= 0; i--) {
            final NeuronLayer currentLayer = neuronalNetwork.get(i);
            final NeuronLayer nextLayer = neuronalNetwork.get(i + 1);
            currentLayer.calculateDeltasFromNextLayer(nextLayer);
        }
    }
}
