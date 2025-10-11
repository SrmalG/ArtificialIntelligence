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
    public static Double calculateCombination(final Double[] data, final Double[] weight, final Double bias) {
        validateCombination(data, weight, bias);
        double suma = 0.0;
        for (int i = 0; i < data.length; ++i)
            suma += data[i] * weight[i];
        return suma + bias;
    }

    /**
     * Validates that the data and weight arrays are not null, have the same length,
     * and that the bias is a positive value.
     *
     * @param data   The input values to validate.
     * @param weight The weights to validate.
     * @param bias   The bias to validate.
     * @throws IllegalArgumentException if the input is invalid.
     */
    private static void validateCombination(final Double[] data, final Double[] weight, Double bias) {
        if (data == null || weight == null || data.length != weight.length || bias == null)
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
    public static Double sigmoidFunction(final Double z) {
        return 1 / (1 + Math.exp(-z));
    }

    /**
     * Executes a forward pass through a single artificial neuron.
     * @param data   The input features to the neuron.
     * @param weight The weight values associated with each input.
     * @param bias   The bias value of the neuron.
     * @return       The activated output of the neuron, in the range (0, 1).
     */
    public static Double forward(final Double[] data, final Double[] weight, final Double bias) {
        Double forwardResult = calculateCombination(data,weight,bias);
        return sigmoidFunction(forwardResult);
    }

}
