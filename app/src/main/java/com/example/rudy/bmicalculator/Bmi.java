package com.example.rudy.bmicalculator;

/**
 * Created by Rudy on 10.03.2018.
 */

public abstract class Bmi {
    protected Double mass, height;
    public Bmi(Double mass, Double height){
        this.mass = mass;
        this.height = height;
    }
    public abstract double calculateBmi() throws IllegalAccessException;
    protected abstract boolean dataAreValid();
}
