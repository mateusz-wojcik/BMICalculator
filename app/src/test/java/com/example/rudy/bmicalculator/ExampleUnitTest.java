package com.example.rudy.bmicalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void bmiSI_isCorrect() {
        BmiForKgM bmi = new BmiForKgM(70.0, 170.0);
        Double bmiValue = bmi.calculateBmi();
        assertEquals(24.22, bmiValue, 0.01);
    }

    @Test
    public void bmiIM_isCorrect(){
        BmiForLbIn bmi = new BmiForLbIn(150.0, 70.0);
        Double bmiValue = bmi.calculateBmi();
        assertEquals(21.52, bmiValue, 0.01);
    }
}