package com.example.demo;

import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronLayer;
import com.example.demo.entitiesAI.NeuronalNetwork;

import static com.example.demo.utilities.Utilities.calculateDeltaErrorLastLayer;


//@SpringBootApplication
public class IntellijenciaArtificialApplication {

	public static void main(String[] args) {
		//SpringApplication.run(IntellijenciaArtificialApplication.class, args);
        final double target = 1;
        final double[] input = {1, 0};

        NeuronalNetwork net = NeuronalNetwork.getInstance();
        NeuronLayer hiddenLayer = new NeuronLayer();

        Neuron h1 = new Neuron(input.length,.3);
        h1.setWeight(new double[]{0.6, -0.1});

        Neuron h2 = new Neuron(input.length,.5);
        h2.setWeight(new double[]{-0.3, 0.4});

        hiddenLayer.addNeuron(h1);
        hiddenLayer.addNeuron(h2);


        NeuronLayer outputLayer = new NeuronLayer();
        Neuron out = new Neuron(hiddenLayer.getNeurons().size(),-.2);
        out.setWeight(new double[]{0.1, 0.4});

        outputLayer.addNeuron(out);

        net.addLayer(hiddenLayer);
        net.addLayer(outputLayer);


        double output = net.forward(input);
        double deltaError = calculateDeltaErrorLastLayer(target,output);
        out.setDeltaError(deltaError);
        System.out.println(out);

        net.putDeltas(new double[]{1.0});
        System.out.println(h2);
        System.out.println(h1);
		//EJEMPLO A MANO -> Aprendizaje -> replicando -> Single layer -> adapter linear neuron -> Mostrar un ejemplo



		//TODO: REPLICAR EL EEJMPLO DE CLASE QUE ESTA EN LAS TRANSPARENCIAS. -> 3 NEURONAS
		//TODO guardar metadatos sobre la fucnion de pérdida en un fichero por epoch -> batch size
		//TODO : configurar cuando ajusto los weights, hyper parametris batch size %%==5
		//TODO DATASET -> entrenamineto -> validación y prueba
	}

	//página 391 -> 393 -> 403 -> cálculo matricial la parte del forward -> Nada de picar y entender
}
