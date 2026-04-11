package com.example.demo.service;

import java.util.ArrayList;

public interface FNNArtificialEngineService {

    ArrayList<Double> trainFNN(double[][] x, double[][] y, int epochs, double learningRate, int[] hiddenLayers);

    double calculate(double[] input);

}
