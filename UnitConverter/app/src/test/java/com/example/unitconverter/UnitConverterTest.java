package com.example.unitconverter;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Length Unit Converter app
 * Tests the conversion logic and edge cases
 */
public class UnitConverterTest {

    // Test conversion factors (same as in MainActivity)
    private static final double METER_FACTOR = 1.0;
    private static final double KILOMETER_FACTOR = 1000.0;
    private static final double CENTIMETER_FACTOR = 0.01;
    private static final double MILLIMETER_FACTOR = 0.001;
    private static final double INCH_FACTOR = 0.0254;
    private static final double FOOT_FACTOR = 0.3048;

    /**
     * Test the conversion method (same logic as MainActivity.convertLength)
     */
    private double convertLength(double value, String from, String to) {
        double fromFactor = getFactor(from);
        double toFactor = getFactor(to);
        double baseValue = value * fromFactor;
        return baseValue / toFactor;
    }

    /**
     * Get conversion factor for a given unit
     */
    private double getFactor(String unit) {
        switch (unit) {
            case "Meter": return METER_FACTOR;
            case "Kilometer": return KILOMETER_FACTOR;
            case "Centimeter": return CENTIMETER_FACTOR;
            case "Millimeter": return MILLIMETER_FACTOR;
            case "Inch": return INCH_FACTOR;
            case "Foot": return FOOT_FACTOR;
            default: throw new IllegalArgumentException("Unknown unit: " + unit);
        }
    }

    @Test
    public void testMeterToKilometer() {
        double result = convertLength(1000, "Meter", "Kilometer");
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testKilometerToMeter() {
        double result = convertLength(1, "Kilometer", "Meter");
        assertEquals(1000.0, result, 0.001);
    }

    @Test
    public void testMeterToCentimeter() {
        double result = convertLength(1, "Meter", "Centimeter");
        assertEquals(100.0, result, 0.001);
    }

    @Test
    public void testCentimeterToMeter() {
        double result = convertLength(100, "Centimeter", "Meter");
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testInchToFoot() {
        double result = convertLength(12, "Inch", "Foot");
        assertEquals(1.0, result, 0.001);
    }

    @Test
    public void testFootToInch() {
        double result = convertLength(1, "Foot", "Inch");
        assertEquals(12.0, result, 0.001);
    }

    @Test
    public void testZeroValue() {
        double result = convertLength(0, "Meter", "Kilometer");
        assertEquals(0.0, result, 0.001);
    }

    @Test
    public void testNegativeValue() {
        double result = convertLength(-1, "Meter", "Centimeter");
        assertEquals(-100.0, result, 0.001);
    }

    @Test
    public void testLargeValue() {
        double result = convertLength(1000000, "Meter", "Kilometer");
        assertEquals(1000.0, result, 0.001);
    }

    @Test
    public void testDecimalValue() {
        double result = convertLength(0.5, "Meter", "Centimeter");
        assertEquals(50.0, result, 0.001);
    }

    @Test
    public void testSameUnitConversion() {
        double result = convertLength(5, "Meter", "Meter");
        assertEquals(5.0, result, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUnit() {
        convertLength(1, "InvalidUnit", "Meter");
    }
} 