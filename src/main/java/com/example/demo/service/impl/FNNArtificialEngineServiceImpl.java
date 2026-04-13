package com.example.demo.service.impl;

import com.example.demo.constants.Constants;
import com.example.demo.dto.ArrayCalculations;
import com.example.demo.dto.CalculateResponse;
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
        final ArrayList<CalculateResponse> calculateResponses = new ArrayList<>(input.length);

        for (double[] doubles : input) {
            double result = net.forward(doubles);
            calculateResponses.add(new CalculateResponse(true, String.format("The result is: %s", result), Utilities.obtainResult(result), doubles));
        }

        return calculateResponses;

    }
}
