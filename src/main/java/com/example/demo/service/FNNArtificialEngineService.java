package com.example.demo.service;

import java.util.ArrayList;

public interface FNNArtificialEngineService {

    ArrayList<Double> trainFNN(double[][] x, double[][] y, int epocs, double learningRate);

    double calculate(double[] input);

}
