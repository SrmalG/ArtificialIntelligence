package com.example.demo.service;

public interface FNNArtificialEngineService {

    void trainFNN(double[][] x, double[][] y, int epocs);

    double calculate(double[] input);

}
