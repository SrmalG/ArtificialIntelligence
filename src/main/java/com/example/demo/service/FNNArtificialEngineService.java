package com.example.demo.service;

import com.example.demo.dto.ArrayCalculations;
import com.example.demo.dto.CalculateResponse;

import java.util.ArrayList;

public interface FNNArtificialEngineService {

    ArrayList<Double> trainFNN(double[][] x, double[][] y, int epochs, double learningRate, int[] hiddenLayers);

    double calculate(double[] input);

    ArrayList<CalculateResponse> calculateArray(double[][] input);
}
