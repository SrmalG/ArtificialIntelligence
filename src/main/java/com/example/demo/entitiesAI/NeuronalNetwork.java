package com.example.demo.entitiesAI;

import java.util.ArrayList;

public class NeuronalNetwork {

    private static NeuronalNetwork instance;
    private ArrayList<NeuronLayer> neuronalNetwork;

    private NeuronalNetwork() {
        this.neuronalNetwork = new ArrayList<>();
    }

    public static NeuronalNetwork getInstance() {
        if (instance == null) {
            instance = new NeuronalNetwork();
        }
        return instance;
    }

    public ArrayList<NeuronLayer> getNeuronalNetwork() {
        return neuronalNetwork;
    }

    public void setNeuronalNetwork(ArrayList<NeuronLayer> neuronalNetwork) {
        this.neuronalNetwork = neuronalNetwork;
    }

    public void addLayer(final NeuronLayer layer) {
        neuronalNetwork.add(layer);
    }

    /**
     * Genera una red neuronal con N capas vacías.
     */
    public NeuronalNetwork generateNeuronalNetwork(int numberOfLayers) {
        if (this.neuronalNetwork == null || this.neuronalNetwork.isEmpty()) {
            this.neuronalNetwork = new ArrayList<>();
            for (int i = 0; i < numberOfLayers; i++) {
                this.neuronalNetwork.add(new NeuronLayer());
            }
        }
        return this;
    }

    /**
     * Ejecuta un forward completo de la red, capa por capa.
     * Cada capa toma la salida de la anterior como entrada.
     */
    public double forward(double[] inputs) {
        double[] output = inputs;
        for (NeuronLayer layer : neuronalNetwork) {
            output = layer.forwardLayer(output);
        }
        return output[0];
    }

    /**
     * Calcula los deltas de toda la red.
     *
     * @param target array con los valores esperados de la última capa
     */
    public void putDeltas(double[] target) {
        if (neuronalNetwork.isEmpty()) return;

        NeuronLayer outputLayer = neuronalNetwork.get(neuronalNetwork.size() - 1);
        for (int i = 0; i < outputLayer.getNeurons().size(); i++) {
            Neuron n = outputLayer.getNeurons().get(i);
            double y = n.getLastOutput();      // salida de la neurona
            double t = target[i];              // valor objetivo
            double delta = (y - t) * y * (1 - y);  // sigmoide + MSE
            n.setDeltaError(delta);
        }

        for (int i = neuronalNetwork.size() - 2; i >= 0; i--) {
            NeuronLayer currentLayer = neuronalNetwork.get(i);
            NeuronLayer nextLayer = neuronalNetwork.get(i + 1);
            currentLayer.calculateDeltasFromNextLayer(nextLayer);
        }
    }
}
