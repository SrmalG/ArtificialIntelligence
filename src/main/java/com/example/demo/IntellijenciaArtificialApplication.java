package com.example.demo;

import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronLayer;
import com.example.demo.entitiesAI.NeuronalNetwork;

import java.util.List;


//@SpringBootApplication
public class IntellijenciaArtificialApplication {

	public static void main(String[] args) {
		//SpringApplication.run(IntellijenciaArtificialApplication.class, args);
        NeuronalNetwork net = NeuronalNetwork.getInstance();

        NeuronLayer hiddenLayer = new NeuronLayer();

        Neuron h1 = new Neuron(2,.2);
        h1.setWeight(new double[]{0.8, -0.4});

        Neuron h2 = new Neuron(2,-.1);
        h2.setWeight(new double[]{0.3, 0.9});

        hiddenLayer.addNeuron(h1);
        hiddenLayer.addNeuron(h2);

        // === Capa de salida (1 neurona, 2 entradas desde la capa oculta) ===
        NeuronLayer outputLayer = new NeuronLayer();

        Neuron out = new Neuron(2,.3);
        out.setWeight(new double[]{0.7, -0.5});
        out.setBias(0.3);

        outputLayer.addNeuron(out);

        // === Agregar capas a la red ===
        net.addLayer(hiddenLayer);
        net.addLayer(outputLayer);

        // === Datos de entrada ===
        double[] input = {0.6, 0.9};

        // === Forward completo ===
        double[] output = net.forward(input);

        // === Resultado final ===
        System.out.println("Salida final de la red: " + output[0]);



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
