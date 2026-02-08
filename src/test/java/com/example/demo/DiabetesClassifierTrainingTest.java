package com.example.demo;

import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronLayer;
import com.example.demo.entitiesAI.NeuronalNetwork;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Synthetic diabetes-like classifier test.
 *
 * IMPORTANT:
 * - This is NOT a medical model.
 * - It's a sanity test that the 3-input NN can learn a simple rule.
 *
 * We use 3 normalized inputs in [0,1] representing (example):
 *  - x1: fasting glucose (normalized)
 *  - x2: BMI (normalized)
 *  - x3: age (normalized)
 *
 * Label rule (synthetic): diabetic if glucose is high AND (BMI or age is high).
 */
public class DiabetesClassifierTrainingTest {

    @Test
    public void trains3FeatureBinaryClassifier() {
        // Dataset: [glucose, bmi, age] all normalized to [0,1]
        final double[][] x = {
                // clearly non-diabetic
                {0.15, 0.20, 0.20},
                {0.25, 0.30, 0.35},
                {0.10, 0.55, 0.25},
                {0.35, 0.15, 0.70},

                // diabetic-like
                {0.85, 0.70, 0.40},
                {0.90, 0.35, 0.85},
                {0.80, 0.80, 0.80},
                {0.75, 0.65, 0.90},

                // borderline / harder
                {0.70, 0.60, 0.30},
                {0.65, 0.25, 0.90}
        };

        final double[][] y = {
                {0},
                {0},
                {0},
                {0},
                {1},
                {1},
                {1},
                {1},
                {1},
                {1}
        };

        final NeuronalNetwork net = NeuronalNetwork.getInstance();
    net.clear();

        // 3 -> 4 -> 1 network
        final NeuronLayer hidden = new NeuronLayer(0.4);
        hidden.addNeuron(new Neuron(3, 0.0));
        hidden.addNeuron(new Neuron(3, 0.0));
        hidden.addNeuron(new Neuron(3, 0.0));
        hidden.addNeuron(new Neuron(3, 0.0));

        final NeuronLayer out = new NeuronLayer(0.4);
        out.addNeuron(new Neuron(4, 0.0));

        net.addLayer(hidden);
        net.addLayer(out);

        net.train(x, y, 60000);

        // A couple of representative sanity points (not from training set)
        final double[] nonDiabetic = {0.20, 0.25, 0.25};
        final double[] diabetic = {0.88, 0.55, 0.60};

        final double pNon = net.forward(nonDiabetic);
        final double pDia = net.forward(diabetic);

        assertTrue("non-diabetic probability should be low, was: " + pNon, pNon < 0.35);
        assertTrue("diabetic probability should be high, was: " + pDia, pDia > 0.65);
    }
}
