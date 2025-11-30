package com.example.demo;

import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronLayer;
import com.example.demo.entitiesAI.NeuronalNetwork;

import java.util.List;

import static com.example.demo.entitiesAI.NeuronLayer.calculateDeltaError;


//@SpringBootApplication
public class IntellijenciaArtificialApplication {

	public static void main(String[] args) {
		//SpringApplication.run(IntellijenciaArtificialApplication.class, args);
        double target = .9;
        NeuronalNetwork net = NeuronalNetwork.getInstance();
        NeuronLayer hiddenLayer = new NeuronLayer();

        Neuron h1 = new Neuron(2,.2);
        h1.setWeight(new double[]{0.8, -0.4});

        Neuron h2 = new Neuron(2,-.1);
        h2.setWeight(new double[]{0.3, 0.9});

        Neuron h3 = new Neuron(2,.8);
        h3.setWeight(new double[]{0.1, 0.1});

        hiddenLayer.addNeuron(h1);
        hiddenLayer.addNeuron(h2);
        hiddenLayer.addNeuron(h3);

        NeuronLayer outputLayer = new NeuronLayer();
        Neuron out = new Neuron(hiddenLayer.getNeurons().size(),.3);
        out.setWeight(new double[]{0.7, -0.5, 0.2});
        Neuron out2 = new Neuron(hiddenLayer.getNeurons().size(),.3);
        out2.setWeight(new double[]{0.9, 0.5, 0.2});


        outputLayer.addNeuron(out);
        outputLayer.addNeuron(out2);


        NeuronLayer realOutputLayer = new NeuronLayer();
        Neuron realOut = new Neuron(outputLayer.getNeurons().size(),.3);
        realOut.setWeight(new double[]{0.2, -0.3});

        realOutputLayer.addNeuron(realOut);


        net.addLayer(hiddenLayer);
        net.addLayer(outputLayer);
        net.addLayer(realOutputLayer);

        double[] input = {0.6, 0.9};

        double output = net.forward(input);

        double deltaError = calculateDeltaError(target,output);

        System.out.println("Salida final de la red: " + output);

        System.out.println("Error: " + deltaError);


		//EJEMPLO A MANO -> Aprendizaje -> replicando -> Single layer -> adapter linear neuron -> Mostrar un ejemplo

		// MPL -> forward



		//TODO: PRIMERO CLASE RED NEURONAL, NEURONA -> Implementar una malla sin backpropagation -> 1.

		//TODO: UNA NEURONA SIMPLE -> 2
		//TODO: REPLICAR EL EEJMPLO DE CLASE QUE ESTA EN LAS TRANSPARENCIAS. -> 3 NEURONAS
		//TODO guardar metadatos sobre la fucnion de pérdida en un fichero por epoch -> batch size
		//TODO : configurar cuando ajusto los weights, hyper parametris batch size %%==5
		//TODO DATASET -> entrenamineto -> validación y prueba
	}

	//página 391 -> 393 -> 403 -> cálculo matricial la parte del forward -> Nada de picar y entender
}
