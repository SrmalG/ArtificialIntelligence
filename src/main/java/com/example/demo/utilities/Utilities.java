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
    public static Double forward(final double[] data, final double[] weight, final double bias,final String activationMode) {
        Double forwardResult = calculateCombination(data,weight,bias);
        switch (activationMode.toUpperCase()) {
            case "SIGMOID" : return sigmoidFunction(forwardResult);
            default: throw new IllegalArgumentException("Not a valid activation function");
        }
    }

    /**
     * Calculates the delta error (δ) used in the backpropagation process for a neuron.
     * <p>
     * This delta represents how much the neuron's output contributed to the total error,
     * taking into account the derivative of the activation function (sigmoid).
     * It is used to update the neuron's weights and bias during training.
     * Here we are using the derivatin of the sigmoid operation
     * </p>
     * <p>
     * Mathematically:
     * <pre>
     *     δ = (forwardResult - target) * forwardResult * (1 - forwardResult)
     * </pre>
     * where:
     * <ul>
     *   <li><b>target</b> — the expected output value (ground truth) from the dataset</li>
     *   <li><b>forwardResult</b> — the actual output value produced by the neuron after activation</li>
     * </ul>
     * </p>
     *
     * @param target        the expected output value (typically 0 or 1 in binary classification)
     * @param forwardResult the neuron's actual output value (between 0 and 1)
     * @return the computed delta error (δ) for backpropagation
     */
    public static Double calculateDeltaError(final Double target, final Double forwardResult){
        return forwardResult-target * forwardResult * (1 - forwardResult);
    }

}
