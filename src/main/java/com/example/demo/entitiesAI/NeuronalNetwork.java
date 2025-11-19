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
    public double[] forward(double[] inputs) {
        double[] output = inputs;
        for (NeuronLayer layer : neuronalNetwork) {
            output = layer.forwardLayer(output);
        }
        return output;
    }
}
