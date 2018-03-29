package com.example.rudy.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final Double BMI_ERROR = -1.0;
    private EditText heightEditText, massEditText;
    private TextView massTextView, heightTextView;
    private Switch unitSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle(getApplicationContext().getResources().getString(R.string.app_title));
        setContentView(R.layout.activity_main);
        setReferences();
        readDataSharedPreferences();
        setUnitsFromSwitch();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setReferences() {
        heightEditText = findViewById(R.id.heightEditText);
        massEditText = findViewById(R.id.massEditText);
        massTextView = findViewById(R.id.massTextView);
        heightTextView = findViewById(R.id.heightTextView);
        unitSwitch = findViewById(R.id.unitSwitch);
    }

    public void calculateBMIButtonOnClick(android.view.View v){
        Double bmi = null;
        bmi = calculateBMI();

        if(bmi == BMI_ERROR) { showIllegalArgumentAlert();}
        else { startDisplayBmiActivity(v, bmi);}
    }

    public void showIllegalArgumentAlert(){
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(getString(R.string.error));
        alertDialog.setMessage(getString(R.string.error_message));
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.spoko),
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which){
                    dialog.dismiss();
                }
            });
        alertDialog.show();
    }

    public void authorButtonOnClick(android.view.View v){
        startAuthorActivity();
    }

    public void switchOnClick(android.view.View v){
        setUnitsFromSwitch();
    }

    public void saveOnClick(MenuItem item) { saveDataSharedPreferences();}

    public void saveDataSharedPreferences(){
        SharedPreferences settings = getApplicationContext().getSharedPreferences("MyPreferences", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("mass", massEditText.getText().toString());
        editor.putString("height", heightEditText.getText().toString());
        editor.putBoolean("switch", unitSwitch.isChecked());
        editor.apply();
    }

    public void readDataSharedPreferences(){
        SharedPreferences settings = getApplicationContext().getSharedPreferences("MyPreferences", 0);
        massEditText.setText(settings.getString("mass", ""));
        heightEditText.setText(settings.getString("height", ""));
        unitSwitch.setChecked(settings.getBoolean("switch", false));
    }

    public void aboutMeMenuOnClick(MenuItem item){
        startAuthorActivity();
    }

    public void startAuthorActivity(){
        Intent intent = new Intent(getApplicationContext(), AuthorActivity.class);
        startActivity(intent);
    }

    public void startDisplayBmiActivity(android.view.View v, Double calculatedBmi){
        Intent intent = new Intent(v.getContext(), DisplayBMIActivity.class);
        intent.putExtra("bmiValue", calculatedBmi);
        startActivity(intent);
    }

    public void setUnitsFromSwitch(){
        if(unitSwitch.isChecked())
            setImprerialUnits();
        else
            setSiUnits();
    }

    public void setImprerialUnits(){
        massTextView.setText(String.format("%s%s", getString(R.string.mass), getString(R.string.pounds)));
        heightTextView.setText(String.format("%s%s", getString(R.string.height), getString(R.string.inches)));
        unitSwitch.setText(R.string.switch_text_imperial);
    }

    public void setSiUnits(){
        massTextView.setText(String.format("%s%s", getString(R.string.mass), getString(R.string.kilograms)));
        heightTextView.setText(String.format("%s%s", getString(R.string.height), getString(R.string.centymeters)));
        unitSwitch.setText(R.string.switch_text_si);
    }

    public Double calculateBMI() {
        Double bmi;
        Bmi bmiClass;
        try {
            bmiClass = unitSwitch.isChecked() ?
                       new BmiForLbIn(Double.parseDouble(massEditText.getText().toString()), Double.parseDouble(heightEditText.getText().toString())) :
                       new BmiForKgM(Double.parseDouble(massEditText.getText().toString()), Double.parseDouble(heightEditText.getText().toString()));

            bmi = bmiClass.calculateBmi();
        } catch (Exception e) {
            e.printStackTrace();
            return BMI_ERROR;
        }

        return bmi;
    }

}
