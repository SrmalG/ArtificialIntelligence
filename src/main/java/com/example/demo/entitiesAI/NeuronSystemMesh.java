package com.example.demo.entitiesAI;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.utilities.Utilities.calculateCombination;
import static com.example.demo.utilities.Utilities.sigmoidFunction;

public class NeuronSystemMesh {

    private ArrayList<Neuron> neurons;
    private double learningRate;
    private String activationMethod;
    private String target;

//    //TODO: Revisar como ajusto los pesos y los bias son para cada neurona no por malla
//    public static void backPropagation(final Neuron neuron, final double deltaError) {
//        for(int i = 0; i < neuron.getWeight().length; ++i) {
//            neuron.getWeight()[i] = neuron.getWeight()[i] - (neuron.getLearningRate() * deltaError * neuron.getData()[i]);
//        }
//        neuron.setBias(neuron.getBias() - neuron.getLearningRate() * deltaError);
//    }
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
    public static double calculateDeltaError(final double target, final double forwardResult){
        return (forwardResult - target) * forwardResult * (1 - forwardResult);
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

    public List<Neuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(ArrayList<Neuron> neurons) {
        this.neurons = neurons;
    }

    public Double forwardsDriver() {
        return null;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
