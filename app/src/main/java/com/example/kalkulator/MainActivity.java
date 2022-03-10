package com.example.kalkulator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class MainActivity extends AppCompatActivity {

    TextView buildTextViews;
    String  mathOperationInProgress = "";
    Button deleteSingleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildTextViews = findViewById(R.id.buildTextViews);
        initLongClickDelete();
        hideSystemBars();
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

    public void equalsOnClick(View view) {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try{
            System.out.println(mathOperationInProgress);
            double result = (double) engine.eval(mathOperationInProgress);
            String temp = String.valueOf(result);
            System.out.println(temp);
            buildTextViews.setText(temp);
            mathOperationInProgress = String.valueOf(result);
        }catch(Exception e){
            Toast.makeText(this,"Wrong build math operation",Toast.LENGTH_LONG).show();
        }

    }

    public void negationOnClick(View view) {
    }
}