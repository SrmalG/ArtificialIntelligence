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
    public ArrayList<Double> trainFNN(double[][] x, double[][] y, int epochs, double learningRate) {
        net = NeuronalNetwork.getInstance();
        net.clear();

        final NeuronLayer hidden = new NeuronLayer(learningRate);
        hidden.addNeuron(new Neuron(3, 0.0));
        hidden.addNeuron(new Neuron(3, 0.0));
        hidden.addNeuron(new Neuron(3, 0.0));
        hidden.addNeuron(new Neuron(3, 0.0));

        final NeuronLayer out = new NeuronLayer(learningRate);
        out.addNeuron(new Neuron(4, 0.0));

        net.addLayer(hidden);
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
