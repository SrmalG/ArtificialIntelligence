package com.example.demo.utilities;


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
        double suma = 0.0;
        for (int i = 0; i < weight.length; ++i)
            suma += data[i] * weight[i];
        return suma + bias;
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

}
