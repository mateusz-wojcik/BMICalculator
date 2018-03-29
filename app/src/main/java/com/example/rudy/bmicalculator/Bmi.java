package com.example.rudy.bmicalculator;

/**
 * Created by Rudy on 10.03.2018.
 */

public abstract class Bmi {

    public static final double MAX_BMI_UNDERWEIGHT = 18.5;
    public static final double MAX_BMI_NORMALWEIGHT = 25.0;

    protected Double mass, height;
    public Bmi(Double mass, Double height){
        this.mass = mass;
        this.height = height;
    }
    public abstract double calculateBmi() throws IllegalAccessException;
    protected abstract boolean dataAreValid();
}
