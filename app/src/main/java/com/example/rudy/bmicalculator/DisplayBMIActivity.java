package com.example.rudy.bmicalculator;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DisplayBMIActivity extends AppCompatActivity {

    public static final double MAX_BMI_UNDERWEIGHT = 18.5;
    public static final double MAX_BMI_NORMALWEIGHT = 25.0;

    private Double bmi;
    private TextView displayBmi;
    private ConstraintLayout layout;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getApplicationContext().getResources().getString(R.string.your_bmi));
        setContentView(R.layout.activity_display_bmi);
        setReferences();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bmi = getIntent().getDoubleExtra("bmiValue", 0);

        displayBmi.setText(bmi.toString());
        setBackgroundColor(getBmiStatus());
    }

    public void setBackgroundColor(BMIStatus status){
        switch (status){
            case UNDERWEIGHT:
                layout.setBackgroundColor(Color.GREEN);
                player = MediaPlayer.create(DisplayBMIActivity.this, R.raw.correctsound);
                player.start();
                break;
            case NORMAL:
                layout.setBackgroundColor(Color.BLUE);
                player = MediaPlayer.create(DisplayBMIActivity.this, R.raw.correctsound);
                player.start();
                break;
            case OVERWEIGHT:
                layout.setBackgroundColor(Color.RED);
                player = MediaPlayer.create(DisplayBMIActivity.this, R.raw.overweightsound);
                player.start();
                break;
        }

    }

    public BMIStatus getBmiStatus(){
        BMIStatus bmiStatus;
        if(bmi < MAX_BMI_UNDERWEIGHT) bmiStatus = BMIStatus.UNDERWEIGHT;
        else if(bmi < MAX_BMI_NORMALWEIGHT) bmiStatus = BMIStatus.NORMAL;
        else bmiStatus = BMIStatus.OVERWEIGHT;

        return bmiStatus;
    }

    public void setReferences(){
        displayBmi = findViewById(R.id.displayBmiTextView);
        layout = findViewById(R.id.activity_display_bmi_layout);
        player = new MediaPlayer();
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
