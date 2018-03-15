package com.example.rudy.bmicalculator;

/**
 * Created by Rudy on 11.03.2018.
 */

public class BmiForLbIn extends Bmi {

    public BmiForLbIn(Double mass, Double height){
        super(mass, height);
    }

    @Override
    public double calculateBmi() throws IllegalArgumentException {
        if(dataAreValid()){
            return Math.round(((mass / (height * height)) * 703.0) * 100.0) / 100.0;
        }
        else {
            throw new IllegalArgumentException("Invalid data");
        }
    }

    @Override
    protected boolean dataAreValid() {
        return mass > 0 && mass < 1000 && height > 0 && height < 150;
    }
}
