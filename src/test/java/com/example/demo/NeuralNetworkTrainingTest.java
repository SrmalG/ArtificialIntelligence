package com.example.demo;

import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronLayer;
import com.example.demo.entitiesAI.NeuronalNetwork;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Minimal learning test to ensure the AI module can actually train.
 *
 * We train a small 2-2-1 network on the AND gate.
 */
public class NeuralNetworkTrainingTest {

    @Test
    public void trainsAndGate() {
        // AND dataset
        final double[][] x = {
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1}
        };
        final double[][] y = {
                {0},
                {0},
                {0},
                {1}
        };

        // Build network (avoid singleton accumulation by clearing first)
        final NeuronalNetwork net = NeuronalNetwork.getInstance();
        net.clear();

        final NeuronLayer hidden = new NeuronLayer(0.5);
        hidden.addNeuron(new Neuron(2, 0.0));
        hidden.addNeuron(new Neuron(2, 0.0));

        final NeuronLayer out = new NeuronLayer(0.5);
        out.addNeuron(new Neuron(2, 0.0));

        net.addLayer(hidden);
        net.addLayer(out);

        // Train
        net.train(x, y, 4000);

        // Predict
        final double p00 = net.forward(x[0]);
        final double p01 = net.forward(x[1]);
        final double p10 = net.forward(x[2]);
        final double p11 = net.forward(x[3]);

        // We don't expect perfect outputs, but the ordering should be right.
        assertTrue("p11 should be high", p11 > 0.7);
        assertTrue("p00 should be low", p00 < 0.3);
        assertTrue("p01 should be low", p01 < 0.3);
        assertTrue("p10 should be low", p10 < 0.3);
    }
}
