package com.example.demo;

import com.example.demo.utilities.Utilities;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Utilities class.
 */
public class UtilsTest {

    private static final double DELTA = 1e-10;

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

}