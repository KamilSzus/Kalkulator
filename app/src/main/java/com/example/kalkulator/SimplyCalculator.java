package com.example.kalkulator;

import android.content.Intent;
import android.os.SystemClock;
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
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

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
            switch (v.getId()){
                case R.id.add:
                    inBuild("+");
                    break;
                case R.id.minus:
                    inBuild("-");
                    break;
                case R.id.multiply:
                    inBuild("*");
                    break;
                case R.id.division:
                    inBuild("/");
                    break;
                case R.id.decimal:
                    inBuild(",");
                    break;
                case R.id.negation:
                    negation();
                    break;
                case R.id.equal:
                    equal();
                    break;
                case R.id.deleteSingle:
                    deleteAllOrSingleChar();
                    break;
                case R.id.deleteAll:
                    mathOperationInProgress = "";
                    buildTextViews.setText("");
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
            } }));
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

    private void equal() {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        try{
            String copy = refactorStringToJavaScriptMathOperation(mathOperationInProgress);
            mathOperationInProgress = String.valueOf(Math.round((double)engine.eval(copy) * 10000.0) / 10000.0);
            if(mathOperationInProgress.equals("NaN")){
                Toast.makeText(this, "Wrong build math operation", Toast.LENGTH_LONG).show();
                return;
            }
            buildTextViews.setText(mathOperationInProgress);
        }catch(Exception e){
            Toast.makeText(this,"Wrong build math operation",Toast.LENGTH_LONG).show();
        }

    }

    private String refactorStringToJavaScriptMathOperation(String mathOperation) {
        mathOperation = mathOperation.replaceAll("S", "Math.s");
        mathOperation = mathOperation.replaceAll("C", "Math.c");
        mathOperation = mathOperation.replaceAll("T", "Math.t");
        mathOperation = mathOperation.replaceAll("L", "Math.l");
        mathOperation = mathOperation.replaceAll("P", "Math.p");
        return mathOperation;
    }

    public void negationOnClick(View view) {
    }

}
