package com.example.demo;

import com.example.demo.utilities.Utilities;
import org.junit.Test;

import static com.example.demo.utilities.Utilities.calculateDeltaErrorLastLayer;
import static org.junit.Assert.*;

/**
 * Unit tests for the Utilities class.
 */
public class UtilsTest {

    private static final double DELTA = 1e-10;

    @Test
    public void testCalculateCombination_NormalCase() {
        double[] data = {1.0, 2.0, 3.0};
        double[] weights = {0.5, -0.2, 1.5};
        double bias = 2.0;

        double result = Utilities.calculateCombination(data, weights, bias);

        assertEquals(6.6, result, DELTA);
    }

    @Test
    public void testCalculateCombination_EmptyArrays() {
        double[] data = {};
        double[] weights = {};
        double bias = 5.0;

        double result = Utilities.calculateCombination(data, weights, bias);
        assertEquals(5.0, result, DELTA);
    }

    @Test
    public void testSigmoidFunction_Zero() {
        double result = Utilities.sigmoidFunction(0.0);
        assertEquals(0.5, result, DELTA);
    }

    @Test
    public void testSigmoidFunction_PositiveLarge() {
        double result = Utilities.sigmoidFunction(100.0);
        // Should be very close to 1
        assertTrue(result > 0.9999999999);
        assertEquals(1.0, result, 1e-10);
    }

    @Test
    public void testSigmoidFunction_NegativeLarge() {
        double result = Utilities.sigmoidFunction(-100.0);
        // Should be very close to 0
        assertTrue(result < 1e-10);
        assertEquals(0.0, result, 1e-10);
    }

    @Test
    public void testSigmoidFunction_TypicalValues() {
        assertEquals(0.7310585786300049, Utilities.sigmoidFunction(1.0), DELTA);
        assertEquals(0.8807970779778823, Utilities.sigmoidFunction(2.0), DELTA);
    }

    @Test
    public void testFullNeuronFlow() {
        double[] inputs = {0.8, 0.4, 0.2};
        double[] weights = {0.5, -1.0, 2.0};
        double bias = -0.3;

        double z = Utilities.calculateCombination(inputs, weights, bias);
        double output = Utilities.sigmoidFunction(z);

        assertEquals(0.1, z, DELTA);
        assertEquals(0.52497918747894, output, 1e-10);
    }

    @Test
    public void testTargetOutputCloseTo1LargePositiveDelta() {
        double error = calculateDeltaErrorLastLayer(0.0, 0.99);
        assertEquals(0.009801, error, DELTA);
    }
}