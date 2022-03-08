package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity {

    TextView buildTextViews;
    TextView resultTextView;
    String  mathOperationInProgress = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findTextView();
    }

    private void findTextView() {
        buildTextViews = findViewById(R.id.buildTextViews);
        resultTextView = findViewById(R.id.resultTextView);
    }

    public void inBuild(String operation){
        mathOperationInProgress += operation;
        buildTextViews.setText(mathOperationInProgress);
    }

    public void clearOnClick(View view) {

    }

    public void powerOnClick(View view) {

    }

    public void multiplyOnClick(View view) {
        inBuild("*");
    }

    public void divisionOnClick(View view) {
        inBuild("/");
    }

    public void sevenOnClick(View view) {
        inBuild("7");
    }

    public void eightOnClick(View view) {
        inBuild("8");
    }

    public void nineOnClick(View view) {
        inBuild("9");
    }

    public void fourOnClick(View view) {
        inBuild("4");
    }

    public void fiveOnClick(View view) {
        inBuild("5");
    }

    public void sixOnClick(View view) {
        inBuild("6");
    }

    public void addOnClick(View view) {
        inBuild("+");
    }

    public void oneOnClick(View view) {
        inBuild("1");
    }

    public void twoOnClick(View view) {
        inBuild("2");
    }

    public void threeOnClick(View view) {
        inBuild("3");
    }

    public void minusOnClick(View view) {
        inBuild("-");
    }

    public void decimalOnClick(View view) {
        inBuild(".");
    }

    public void zeroOnClick(View view) {
        inBuild("0");
    }

    public void bracketsOnClick(View view) {
        //TODO:inBuild("7");
    }

    public void equalsOnClick(View view) {
        double result = 0.0;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try{
            System.out.println(mathOperationInProgress);
            result = (double) engine.eval(mathOperationInProgress);
        }catch(Exception e){
            Toast.makeText(this,"Wrong build math operation",Toast.LENGTH_LONG).show();
        }
        mathOperationInProgress = "";
        resultTextView.setText( String.valueOf(result));
    }

    public void clearAllOnClick(View view) {
    }

    public void negationOnClick(View view) {
    }
}