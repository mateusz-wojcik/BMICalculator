package com.example.rudy.bmicalculator;

/**
 * Created by Rudy on 11.03.2018.
 */

public class BmiForKgM extends Bmi{

    public BmiForKgM(Double mass, Double height){
        super(mass, height);
    }

    @Override
    public double calculateBmi() throws IllegalArgumentException {
        if(dataAreValid()){
            return Math.round((mass / (height / 100.0 * height / 100.0))* 100.0) / 100.0;
        }
        else {
            throw new IllegalArgumentException("Invalid data");
        }
    }

    @Override
    protected boolean dataAreValid() {
        return mass > 0 && mass < 500 && height > 0 && height < 300;
    }


}
