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
		double[] data = {1.0, 0.0, 1.0};
		double[] weight = {0.8, -0.5};
		double bias = 1.0;
		neuron.setBias(bias);
		neuron.setData(data);
		neuron.setWeight(weight);
		neuron.setTarget(data[data.length-1]);
		neuron.setActivactionMethod("Sigmoid");
		Double result = Utilities.forward(neuron.getData(), neuron.getWeight(), neuron.getBias(),neuron.getActivationMethod());
		System.out.println(result);

	}

}
