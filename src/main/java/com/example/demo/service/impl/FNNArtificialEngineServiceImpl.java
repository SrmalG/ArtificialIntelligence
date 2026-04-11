package com.example.demo.service.impl;

import com.example.demo.constants.Constants;
import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronLayer;
import com.example.demo.entitiesAI.NeuronalNetwork;
import com.example.demo.service.FNNArtificialEngineService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FNNArtificialEngineServiceImpl implements FNNArtificialEngineService {

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
                layer.addNeuron(new Neuron(inputDim, 0.0));
            }
            net.addLayer(layer);
            inputDim = size;
        }

        final NeuronLayer out = new NeuronLayer(learningRate);
        out.addNeuron(new Neuron(inputDim, 0.0));
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


}
