package com.example.demo;

import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronLayer;
import com.example.demo.entitiesAI.NeuronalNetwork;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ForwardPassManualExampleTest {

    private static final double EPS = 1e-3; // rounding in the statement is to 4 decimals

    @Test
    public void forwardPassMatchesManualComputation() {
        final double[] input = {0, 1};

        final NeuronalNetwork net = NeuronalNetwork.getInstance();
        net.clear();

        final NeuronLayer hidden = new NeuronLayer(0.0);
        final Neuron z1 = new Neuron(2, 0.3);
        final Neuron z2 = new Neuron(2, 0.5);

        z1.setWeight(new double[]{0.6, -0.1});
        z2.setWeight(new double[]{-0.3, 0.4});

        hidden.addNeuron(z1);
        hidden.addNeuron(z2);

        final NeuronLayer output = new NeuronLayer(0.1);
        final Neuron out = new Neuron(2, -0.2);
        out.setWeight(new double[]{0.4, 0.1});
        output.addNeuron(out);

        net.addLayer(hidden);
        net.addLayer(output);

        final double y = net.forward(input);

        assertEquals(0.5227, y, EPS);
    }
}
