package com.example.demo.utilities;


public class Utilities {

    private Utilities() {}

    public static Double calculateCombination(final Double[] data, final Double[] weight, final Double bias) {
        validateCombination(data, weight, bias);
        double suma = 0.0;
        for (int i = 0; i < data.length; ++i)
            suma += data[i] * weight[i];
        return suma + bias;
    }


    public static void validateCombination(final Double[] data, final Double[] weight, Double bias) {
        if (data != null && weight != null && data.length != weight.length && bias > 0)
            throw new IllegalArgumentException("Weight and data must be the same and must be informed");
    }

}
