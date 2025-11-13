package com.example.demo;

import com.example.demo.entitiesAI.Neuron;
import com.example.demo.entitiesAI.NeuronSystemMesh;
import com.example.demo.utilities.Utilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import static com.example.demo.utilities.Utilities.generateMesh;

//@SpringBootApplication
public class IntellijenciaArtificialApplication {

	public static void main(String[] args) {
		//SpringApplication.run(IntellijenciaArtificialApplication.class, args);
		Neuron neuron = new Neuron();
		double[] data = {1.0, 0.0, 1.0};
		double[] weight = {0.8, -0.5};
		double bias = 1.0;
		neuron.setBias(bias);
		neuron.setData(data);
		neuron.setWeight(weight);
//		neuron.setTarget(data[data.length-1]);
//		neuron.setActivactionMethod("Sigmoid");
		NeuronSystemMesh mesh = generateMesh();

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
