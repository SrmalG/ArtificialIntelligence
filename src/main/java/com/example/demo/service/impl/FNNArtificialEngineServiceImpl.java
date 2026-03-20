package com.example.demo.service.impl;

import com.example.demo.constants.Constants;
import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronLayer;
import com.example.demo.entitiesAI.NeuronalNetwork;
import com.example.demo.service.FNNArtificialEngineService;
import org.springframework.stereotype.Service;

@Service
public class FNNArtificialEngineServiceImpl implements FNNArtificialEngineService {

    private NeuronalNetwork net;

    @Override
    public void trainFNN(double[][] x, double[][] y, int epocs) {
        net = NeuronalNetwork.getInstance();
        net.clear();

        final NeuronLayer hidden = new NeuronLayer(0.4);
        hidden.addNeuron(new Neuron(3, 0.0));
        hidden.addNeuron(new Neuron(3, 0.0));
        hidden.addNeuron(new Neuron(3, 0.0));
        hidden.addNeuron(new Neuron(3, 0.0));

        final NeuronLayer out = new NeuronLayer(0.4);
        out.addNeuron(new Neuron(4, 0.0));

        net.addLayer(hidden);
        net.addLayer(out);

        net.train(x, y, epocs);
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
