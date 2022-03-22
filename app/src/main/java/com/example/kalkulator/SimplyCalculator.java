package com.example.kalkulator;

import android.content.Intent;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import net.objecthunter.exp4j.ExpressionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SimplyCalculator extends AppCompatActivity {
    protected TextView buildTextViews;
    protected String  mathOperationInProgress = "";
    protected Button backToMenu;
    private final List<Numbers> numbersList = new ArrayList<>();
    private final List<SimplyOperation> simplyOperationList = new ArrayList<>();
    private long timestampLastClick;


    protected void initSimplyOperation() {
        simplyOperationList.add(new SimplyOperation("add",findViewById(R.id.add)));
        simplyOperationList.add(new SimplyOperation("minus",findViewById(R.id.minus)));
        simplyOperationList.add(new SimplyOperation("multiply",findViewById(R.id.multiply)));
        simplyOperationList.add(new SimplyOperation("division",findViewById(R.id.division)));
        simplyOperationList.add(new SimplyOperation("decimal",findViewById(R.id.decimal)));
        simplyOperationList.add(new SimplyOperation("negation",findViewById(R.id.negation)));
        simplyOperationList.add(new SimplyOperation("equal",findViewById(R.id.equal)));
        simplyOperationList.add(new SimplyOperation("deleteSingle",findViewById(R.id.deleteSingle)));
        simplyOperationList.add(new SimplyOperation("deleteAll",findViewById(R.id.deleteAll)));
    }

    protected void initNumbersButton() {
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

    protected void onClickSimplyOperation() {
        simplyOperationList.forEach(operation -> operation.getOperationButton().setOnClickListener(v -> {
            switch (operation.getOperationButton().getText().toString()){
                case "+/-":
                    negation();
                    break;
                case "=":
                    equal();
                    break;
                case "C":
                    deleteAllOrSingleChar();
                    break;
                case "AC":
                    mathOperationInProgress = "";
                    buildTextViews.setText("");
                    break;
                default:
                    inBuild(operation.getOperationButton().getText().toString());
                    break;
            }
        }));
        simplyOperationList.forEach(simplyOperation -> simplyOperation.getOperationButton().setOnLongClickListener(v -> {
            if(v.getId()==R.id.deleteSingle){
                mathOperationInProgress = "";
                buildTextViews.setText("");
            }
            return true;
        }));
    }

    private void negation() {
        //TODO:Wymyslic cos lepszego
        if(mathOperationInProgress.endsWith("-")){
            mathOperationInProgress=mathOperationInProgress.substring(0,mathOperationInProgress.length()-1);
            inBuild("+");
        }
        else {
            if(mathOperationInProgress.endsWith("+")){
                mathOperationInProgress=mathOperationInProgress.substring(0,mathOperationInProgress.length()-1);
            }
            inBuild("-");
        }
    }

    private void deleteAllOrSingleChar() {
        if((SystemClock.elapsedRealtime() - timestampLastClick) < 200) {
            mathOperationInProgress = "";
            buildTextViews.setText("");
            return;
        }
        timestampLastClick = SystemClock.elapsedRealtime();
        mathOperationInProgress = deleteLastSign();
        buildTextViews.setText(mathOperationInProgress);
    }

    protected void onClickNumbers() {
        numbersList.forEach(number -> number.getNumberButtonId().setOnClickListener(v -> {
            inBuild(number.getNumberButtonId().getText().toString());
            }));
    }

    protected void initMenuButton() {
        backToMenu = findViewById(R.id.menu);
        backToMenu.setOnClickListener(v -> {
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        });
    }

    protected void hideSystemBars() {
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());
        if (windowInsetsController == null) {
            return;
        }
        windowInsetsController.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
    }

    protected void inBuild(String operation){
        if(mathOperationInProgress.length()<=15) {
            mathOperationInProgress += operation;
            buildTextViews.setText(mathOperationInProgress);
        }else{
            Toast.makeText(this, "max size of input", Toast.LENGTH_LONG).show();
        }
    }

    private String deleteLastSign() {
        return Optional.ofNullable(mathOperationInProgress)
                .filter(str -> str.length() != 0)
                .map(str -> str.substring(0, str.length() - 1))
                .orElse(mathOperationInProgress);
    }

    public void equal() {

        try{
            double result = new ExpressionBuilder(mathOperationInProgress)
                    .build()
                    .evaluate();
            mathOperationInProgress=String.valueOf(Math.round(result * 10000.0) / 10000.0);
            buildTextViews.setText(mathOperationInProgress);
        }catch(ArithmeticException|IllegalArgumentException e){
            Toast.makeText(this,"Wrong build math operation",Toast.LENGTH_LONG).show();
        }

    }

}
