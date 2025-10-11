package com.example.demo;

import com.example.demo.entitiesAI.Neuron;
import com.example.demo.utilities.Utilities;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class IntellijenciaArtificialApplication {

	public static void main(String[] args) {
		//SpringApplication.run(IntellijenciaArtificialApplication.class, args);

		Neuron neuron = new Neuron();
		Double[] data = {1.0, 0.0};
		Double[] weight = {0.8, -0.5};
		Double bias = 1.0;
		neuron.setBias(bias);
		neuron.setData(data);
		neuron.setWeight(weight);
		neuron.setActivactionMethod("Sigmoid");
		Double result = Utilities.forward(neuron.getData(), neuron.getWeight(), neuron.getBias());
		System.out.println(result);
	}

}
