package com.example.demo.utilities;


import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronSystemMesh;

import java.util.ArrayList;

public class Utilities {

    private Utilities() {}

    /**
     * Calculates the linear combination of the input data and weights, including the bias.
     * This method computes the value z in a neuron:
     *      z = w1*x1 + w2*x2 + ... + wn*xn + bias
     *
     * @param data   The input values (features) to the neuron.
     * @param weight The weight values associated with each input.
     * @param bias   The bias term that shifts the activation threshold.
     * @return       The result of the linear combination (z).
     * @throws IllegalArgumentException if data and weight are not the same length or if bias is not valid.
     */
    public static Double calculateCombination(final double[] data, final double[] weight, final double bias) {
        validateCombination(data, weight);
        double suma = 0.0;
        for (int i = 0; i < weight.length; ++i)
            suma += data[i] * weight[i];
        return suma + bias;
    }

    /**
     * Validates that the data and weight arrays are not null, have the same length,
     * and that the bias is a positive value.
     *
     * @param data   The input values to validate.
     * @param weight The weights to validate.
     * @throws IllegalArgumentException if the input is invalid.
     */
    private static void validateCombination(final double[] data, final double[] weight) {
        if (data == null || weight == null || data.length-1 != weight.length)
            throw new IllegalArgumentException("Weight and data must be the same and must be informed");
    }

    /**
     * Applies the sigmoid activation function to the input value.
     *
     * The sigmoid function is defined as:
     *      σ(z) = 1 / (1 + e^(-z))
     *
     * It maps any real-valued input into a value between 0 and 1, useful for binary classification.
     *
     * @param z The input value (typically the result of a linear combination).
     * @return  The sigmoid-activated output.
     */
    public static double sigmoidFunction(final double z) {
        return 1 / (1 + Math.exp(-z));
    }

    public static NeuronSystemMesh generateMesh() {
        NeuronSystemMesh mesh = new NeuronSystemMesh();
        ArrayList<Neuron> neuronas = new ArrayList<>(10);
        Neuron neuron = new Neuron();
        neuron.setNeuronId(0);
        neuron.setConnectedTo(null);
        neuron.setBias(.4);
        double[] data = {1.0, 0.0, 1.0};
        double[] weight = {0.8, -0.5};
        double bias = 1.0;
        neuron.setBias(bias);
        neuron.setData(data);
        neuron.setWeight(weight);
        neuronas.add(neuron);
        mesh.setNeurons(neuronas);
        return mesh;
    }





}
