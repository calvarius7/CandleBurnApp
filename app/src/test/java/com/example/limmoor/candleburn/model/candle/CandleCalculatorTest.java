package com.example.limmoor.candleburn.model.candle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CandleCalculatorTest {

    private Candle candle;
    private CandleCalculator candleCalculator;

    @BeforeEach
    void setUp() {
        candleCalculator = new CandleCalculator(new Candle());
        candle = candleCalculator.getCandle();
    }

    @Test
    @DisplayName("Candle.weight = 1.0 => 1.0")
    void calcWeight() {
        candle.setWeight(1.0);
        assertEquals(1.0, candleCalculator.calcWeight());
    }

    @Test
    @DisplayName("Candle.weight = -1.0 => 0.0")
    void calcNegativeWeight() {
        candle.setWeight(-1.0);
        candle.setMaterial(Material.BEEWAX);
         assertEquals(0.0, candleCalculator.calcWeight());
    }

    @Test
    @DisplayName("Height = 1, diameter = 2, density = 0.9 => 2.827 ")
    void calcWeightWithHeightAndDens() {
        candle.setMaterial(Material.PARAFIN);
        candle.setLength(1.0);
        candle.setDiameter(2.0);
        assertEquals(2.827, candleCalculator.calcWeight(), 0.01);
    }

    @Test
    @DisplayName("Height = 10.1, diameter = 10.1, density = 0.93 => 752.5527")
    void calcWeightWithDigits() {
        candle.setMaterial(Material.STEARIN);
        candle.setLength(10.1);
        candle.setDiameter(10.1);
        assertEquals(752.5527, candleCalculator.calcWeight(), 0.001);
    }

    @Test
    @DisplayName("Height = 0, diameter = 0, density = 0.9 => 0 ")
    void calcWeightZero() {
        candle.setMaterial(Material.PARAFIN);
        candle.setLength(0);
        candle.setDiameter(0);
        assertEquals(0.0, candleCalculator.calcWeight());
    }

    @Test
    @DisplayName("Height = -1, diameter = -1, density = 0.9 => 0 ")
    void calcWeightNegative() {
        candle.setMaterial(Material.PARAFIN);
        candle.setLength(-1);
        candle.setDiameter(-1);
        assertEquals(0.0, candleCalculator.calcWeight());
    }

    @Test
    @DisplayName("Weight = 7.5, Speed = 7.5g/h => 1")
    void calcDuration() {
        candle.setWeight(7.5);
        candle.setMaterial(Material.PARAFIN);
        assertEquals(1, candleCalculator.calcDuration());
    }

    @Test
    @DisplayName("Weight = 2.0, Speed = 4.0g/h => 0.5")
    void calcHalfDuration() {
        candle.setWeight(2.0);
        candle.setMaterial(Material.BEEWAX);
        assertEquals(0.5, candleCalculator.calcDuration());
    }

    @Test
    @DisplayName("Weight = -2.0, Speed = -4.0g/h => 0.0")
    void calcNegativeDuration() {
        candle.setWeight(-2.0);
        candle.setMaterial(Material.BEEWAX);
        assertEquals(0.0, candleCalculator.calcDuration());
    }


    @Test
    @DisplayName("Height = 1, diameter = 2, density = 0.9 => 0 hour 22 minutes")
    void calcWeightWithHeightAndDensThenDuration() {
        candle.setMaterial(Material.PARAFIN);
        candle.setLength(1.0);
        candle.setDiameter(2.0);
        assertEquals("0:22", candleCalculator.getTimeLeft());
    }

    @Test
    @DisplayName("Material = null => 0.0")
    void testNull(){
        assertEquals(0.0, candleCalculator.calcDuration() );
    }

}