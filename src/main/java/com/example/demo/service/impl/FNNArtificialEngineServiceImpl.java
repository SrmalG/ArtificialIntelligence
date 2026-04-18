package com.example.demo.service.impl;

import com.example.demo.constants.Constants;
import com.example.demo.dto.CalculateResponse;
import com.example.demo.dto.MetricsInDto;
import com.example.demo.dto.MetricsOutDto;
import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronLayer;
import com.example.demo.entitiesAI.NeuronalNetwork;
import com.example.demo.service.FNNArtificialEngineService;
import com.example.demo.utilities.Utilities;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@Service
public class FNNArtificialEngineServiceImpl implements FNNArtificialEngineService {

    private static final Random RNG = new Random(42);
    private NeuronalNetwork net;

    @Override
    public ArrayList<Double> trainFNN(double[][] x, double[][] y, int epochs, double learningRate, int[] hiddenLayers) {
        if (x == null || x.length == 0)
            throw new IllegalArgumentException(Constants.NO_DATA_INFORMED);
        if (hiddenLayers == null || hiddenLayers.length == 0)
            throw new IllegalArgumentException("At least one hidden layer is required");

        net = NeuronalNetwork.getInstance();
        net.clear();

        int inputDim = x[0].length;

        for (int size : hiddenLayers) {
            final NeuronLayer layer = new NeuronLayer(learningRate);
            for (int i = 0; i < size; i++) {
                double bias = RNG.nextDouble() * 0.02 - 0.01;
                layer.addNeuron(new Neuron(inputDim, bias));
            }
            net.addLayer(layer);
            inputDim = size;
        }

        final NeuronLayer out = new NeuronLayer(learningRate);
        out.addNeuron(new Neuron(inputDim, RNG.nextDouble() * 0.02 - 0.01));
        net.addLayer(out);

        return net.train(x, y, epochs);
    }

    @Override
    public double calculate(double[] input) {
        if(net == null)
            throw new IllegalArgumentException(Constants.MODEL_NOT_TRAINED);
        if(input.length == 0)
            throw new IllegalArgumentException(Constants.NO_DATA_INFORMED);

        return net.forward(input);
    }

    @Override
    public ArrayList<CalculateResponse> calculateArray(double[][] input) {
        if (net == null)
            throw new IllegalArgumentException(Constants.MODEL_NOT_TRAINED);
        if (input == null || input.length == 0)
            throw new IllegalArgumentException(Constants.NO_DATA_INFORMED);

        final ArrayList<CalculateResponse> calculateResponses = new ArrayList<>(input.length);

        for (double[] doubles : input) {
            final double result = net.forward(doubles);
            calculateResponses.add(new CalculateResponse(true, String.format("The result is: %s", result), Utilities.obtainResult(result), doubles));
        }

        return calculateResponses;
    }

    @Override
    public MetricsOutDto calculateMetrics(final MetricsInDto in) {
        if (in.getPredictions().length != in.getTargets().length) {
            throw new IllegalArgumentException("Predictions and targets must have the same length");
        }

        // Inicializar la matriz de confusión
        final double[][] matrixConf = new double[2][2];

        int truePositive = 0;
        int trueNegative = 0;
        int falsePositive = 0;
        int falseNegative = 0;

        // Calcular la matriz de confusión
        for (int i = 0; i < in.getPredictions().length; i++) {
            int predicted = in.getPredictions()[i] >= in.getThreshold() ? 1 : 0;
            int actual = (int) in.getTargets()[i];

            if (predicted == 1 && actual == 1) {
                truePositive++;
            } else if (predicted == 0 && actual == 0) {
                trueNegative++;
            } else if (predicted == 1 && actual == 0) {
                falsePositive++;
            } else if (predicted == 0 && actual == 1) {
                falseNegative++;
            }
        }

        // Asignar valores a la matriz de confusión
        matrixConf[0][0] = truePositive; // TP
        matrixConf[0][1] = falsePositive; // FP
        matrixConf[1][0] = falseNegative; // FN
        matrixConf[1][1] = trueNegative; // TN

        // Calcular métricas
        int total = truePositive + trueNegative + falsePositive + falseNegative;
        double accuracy = (double) (truePositive + trueNegative) / total; // Mide la proporción de prediccines del modelo correctas
        double sensitivity = (double) truePositive / (truePositive + falseNegative); // Mide la capacidad del modelo para identificar instancias correctas
        double specificity = (double) trueNegative / (trueNegative + falsePositive);  // Mide la capacidad del modelo para identificar instancias falsas
        double balancedAccuracy = (sensitivity + specificity) / 2; // Mide la relación entre la sensitivity y specificity.
        double precision = (double) truePositive / (truePositive + falsePositive); // proporción de predicciones positivas verdaderas
        double f1Score = 2 * (precision * sensitivity) / (precision + sensitivity); // El balance entre la precisión y la sensibilidad.


        // Retornar las métricas calculadas
        return new MetricsOutDto(matrixConf, accuracy, f1Score, balancedAccuracy);
    }
}
