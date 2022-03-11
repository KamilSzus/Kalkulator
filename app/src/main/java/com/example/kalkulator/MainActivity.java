package com.example.kalkulator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity {

    TextView buildTextViews;
    String  mathOperationInProgress = "";
    Button deleteSingleButton;
    List<Numbers> numbersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildTextViews = findViewById(R.id.buildTextViews);
        initLongClickDelete();
        hideSystemBars();
        initNumbersButton();
        numbersList.forEach(number -> number.getNumberButtonId().setOnClickListener(v -> {
            switch (v.getId()){
                case R.id.zero:
                    inBuild("0");
                    break;
                case R.id.one:
                    inBuild("1");
                    break;
                case R.id.two:
                    inBuild("2");
                    break;
                case R.id.three:
                    inBuild("3");
                    break;
                case R.id.four:
                    inBuild("4");
                    break;
                case R.id.five:
                    inBuild("5");
                    break;
                case R.id.six:
                    inBuild("6");
                    break;
                case R.id.seven:
                    inBuild("7");
                    break;
                case R.id.eight:
                    inBuild("8");
                    break;
                case R.id.nine:
                    inBuild("9");
                    break;
            }
        }));

    }

    private void initNumbersButton() {
        numbersList.add(new Numbers("zero",findViewById(R.id.zero)));
        numbersList.add(new Numbers("one",findViewById(R.id.one)));
        numbersList.add(new Numbers("two",findViewById(R.id.two)));
        numbersList.add(new Numbers("three",findViewById(R.id.three)));
        numbersList.add(new Numbers("four",findViewById(R.id.four)));
        numbersList.add(new Numbers("five",findViewById(R.id.five)));
        numbersList.add(new Numbers("six",findViewById(R.id.six)));
        numbersList.add(new Numbers("seven",findViewById(R.id.seven)));
        numbersList.add(new Numbers("eight",findViewById(R.id.eight)));
        numbersList.add(new Numbers("nine",findViewById(R.id.nine)));
    }

    private void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
    }


    private void initLongClickDelete() {
        deleteSingleButton = findViewById(R.id.deleteSingle);
        if(deleteSingleButton!=null) {
            deleteSingleButton.setOnLongClickListener(v -> {
                mathOperationInProgress = "";
                buildTextViews.setText("");
                return true;
            });
            deleteSingleButton.setOnClickListener(v -> {
                mathOperationInProgress = deleteLastSign();
                buildTextViews.setText(mathOperationInProgress);
            });
        }
    }

    public void inBuild(String operation){
        mathOperationInProgress += operation;
        buildTextViews.setText(mathOperationInProgress);
    }

    public void clearOnClick(View view) {
        mathOperationInProgress = deleteLastSign();
        buildTextViews.setText(mathOperationInProgress);
    }

    private String deleteLastSign() {
        return Optional.ofNullable(mathOperationInProgress)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - 1))
                .orElse(mathOperationInProgress);
    }

    public void clearAllOnClick(View view) {
        mathOperationInProgress = "";
        buildTextViews.setText("");
    }


    public void multiplyOnClick(View view) {
        inBuild("*");
    }

    public void divisionOnClick(View view) {
        inBuild("/");
    }

    public void addOnClick(View view) {
        inBuild("+");
    }

    public void minusOnClick(View view) {
        inBuild("-");
    }

    public void decimalOnClick(View view) {
        inBuild(".");
    }

    public void equalsOnClick(View view) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try{
            System.out.println(mathOperationInProgress);
            double result = (double) engine.eval(mathOperationInProgress);
            String temp = String.valueOf(result);
            //System.out.println(temp);
            buildTextViews.setText(temp);
            mathOperationInProgress = String.valueOf(result);
        }catch(Exception e){
            Toast.makeText(this,"Wrong build math operation",Toast.LENGTH_LONG).show();
        }

    }

    public void negationOnClick(View view) {
    }

    public void sinOnClick(View view) {
    }

    public void cosOnClick(View view) {

    }

    public void tanOnClick(View view) {
    }

    public void lnOnClick(View view) {
    }

    public void sqrtOnClick(View view) {
    }

    public void powTwoOnClick(View view) {
    }

    public void powYOnClick(View view) {
    }

    public void logOnClick(View view) {
    }
}