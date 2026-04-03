package com.example.demo.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Arrays;

public class SimpleCalculation implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private double[] input;

    public double[] getInput() {
        return input;
    }

    public void setInput(double[] input)  {
        this.input = input;
    }

    @Override
    public String toString() {
        return "SimpleCalculation{" +
                "input=" + Arrays.toString(input) +
                '}';
    }
}

