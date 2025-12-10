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
     * @since 1.0
     */
    public static double calculateCombination(final double[] data, final double[] weight, final double bias) {
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

    /**
     * Calcula el error delta (δ) de una neurona de salida en una red neuronal con función de activación sigmoide.
     *
     * <p>Este valor es esencial en el algoritmo de <strong>backpropagation</strong> y representa la derivada parcial
     * del error/respecto al potencial de activación (z) de la neurona. Para la función sigmoide y el error cuadrático medio,
     * la fórmula simplificada es:</p>
     *
     * <pre>
     *     δ = (ŷ - y) × σ'(z) = (ŷ - y) × ŷ × (1 - ŷ)
     * </pre>
     *
     * <p>donde:</p>
     * <ul>
     *   <li><strong>ŷ</strong> ({@code forwardResult}) → salida predicha por la neurona (valor después de aplicar sigmoide)</li>
     *   <li><strong>y</strong> ({@code target}) → valor objetivo o etiqueta real (0 o 1 en clasificación binaria)</li>
     *   <li><strong>σ'(z)</strong> → derivada de la sigmoide = ŷ × (1 - ŷ)</li>
     * </ul>
     *
     * <p>Este cálculo asume que la función de pérdida es el <strong>error cuadrático medio</strong> y que la capa de salida
     * usa la función de activación <strong>sigmoide</strong>. Si usas otra función de pérdida (por ejemplo, entropía cruzada binaria),
     * la expresión se simplifica aún más y este factor {@code ŷ × (1 - ŷ)} desaparece.</p>
     *
     * @param target         el valor objetivo o etiqueta real (generalmente 0.0 o 1.0)
     * @param forwardResult  la salida de la neurona tras aplicar la función sigmoide (valor en el rango (0,1))
     * @return               el error delta (δ) de la neurona, usado para propagar el error hacia atrás
     *
     * @see Utilities#sigmoidFunction(double)
     * @since 1.0
     */
    public static double calculateDeltaErrorLastLayer(final double target, final double forwardResult) {
        return (forwardResult - target) * forwardResult * (1 - forwardResult);
    }

}
