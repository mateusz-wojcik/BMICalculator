package com.example.rudy.bmicalculator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayBMIActivity extends AppCompatActivity {

    private Double bmi;
    private BMIStatus bmiStatus;
    private TextView displayBmi;
    private TextView displayBmiStatus;
    private ConstraintLayout layout;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getApplicationContext().getResources().getString(R.string.your_bmi));
        setContentView(R.layout.activity_display_bmi);
        setReferences();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        activeSetMethods();
    }

    public void activeSetMethods(){
        setBmi(getIntent().getDoubleExtra("bmiValue", 0));
        setBmiStatus(findBmiStatus());
        setBmiStatusDisplayText(findBmiStatusDisplayText(getBmiStatus()));
        setDisplayBmiText(bmi.toString());
        setBackgroundColor(getBmiStatus());
        setSound(getBmiStatus());
    }

    public void setDisplayBmiText(String text){
        displayBmi.setText(text);
    }

    public void setBmi(Double bmi){
        this.bmi = bmi;
    }

    //zacne efekty dzwiekowe
    public void playSound(int soundId){
        player = MediaPlayer.create(DisplayBMIActivity.this, soundId);
        player.start();

    }

    public BMIStatus getBmiStatus(){
        return bmiStatus;
    }

    public void setBmiStatus(BMIStatus bmiStatus){
        this.bmiStatus = bmiStatus;
    }

    public void setBmiStatusDisplayText(String text){
        displayBmiStatus.setText(text);
    }

    public BMIStatus findBmiStatus(){
        BMIStatus bmiStatus;
        if(bmi < Bmi.MAX_BMI_UNDERWEIGHT) bmiStatus = BMIStatus.UNDERWEIGHT;
        else if(bmi < Bmi.MAX_BMI_NORMALWEIGHT) bmiStatus = BMIStatus.NORMAL;
        else bmiStatus = BMIStatus.OVERWEIGHT;

        return bmiStatus;
    }

    public void setReferences(){
        displayBmi = findViewById(R.id.displayBmiTextView);
        layout = findViewById(R.id.activity_display_bmi_layout);
        displayBmiStatus = findViewById(R.id.displayBmiStatusTextView);
        player = new MediaPlayer();
    }


    public void setBackgroundColor(BMIStatus status){
        switch (status){
            case UNDERWEIGHT:
                layout.setBackgroundColor(Color.GREEN);
                break;
            case NORMAL:
                layout.setBackgroundColor(Color.BLUE);
                break;
            case OVERWEIGHT:
                layout.setBackgroundColor(Color.RED);
                break;
        }

    }

    public void setSound(BMIStatus status){
        switch (status){
            case UNDERWEIGHT:
            case NORMAL:
                playSound(R.raw.correctsound);
                break;
            case OVERWEIGHT:
                playSound(R.raw.overweightsound);
                break;
        }

    }

    public String findBmiStatusDisplayText(BMIStatus bmiStatus){
        String result = null;
        switch (bmiStatus){
            case UNDERWEIGHT:
                result = getString(R.string.underweight);
                break;
            case NORMAL:
                result = getString(R.string.normalweight);
                break;
            case OVERWEIGHT:
                result = getString(R.string.overweight);
                break;
        }

        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        finish();
        return true;
    }

    @Override
    protected void onStop(){
        if(player.isPlaying()) player.stop();
        super.onStop();
    }

}
