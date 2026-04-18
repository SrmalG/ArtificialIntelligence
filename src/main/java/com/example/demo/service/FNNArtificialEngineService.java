package com.example.demo.service;

import com.example.demo.dto.*;

import java.util.ArrayList;

public interface FNNArtificialEngineService {

    ArrayList<Double> trainFNN(double[][] x, double[][] y, int epochs, double learningRate, int[] hiddenLayers);

    double calculate(double[] input);

    CalculateArrayResponseDto calculateArray(ArrayCalculations data);

    MetricsOutDto calculateMetrics(final MetricsInDto in);

}
