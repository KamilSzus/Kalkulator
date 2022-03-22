package com.example.kalkulator;

import android.os.Bundle;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AdvanceCalculator extends SimplyCalculator {

    List<AdvanceOperation> operationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_calculator);
        buildTextViews = findViewById(R.id.buildTextViews);

        initMenuButton();
        hideSystemBars();
        initNumbersButton();
        onClickNumbers();
        initSimplyOperation();
        onClickSimplyOperation();
        initAdvanceOperation();

        onClickAdvanceCalculatorButtons();
        if(savedInstanceState!=null){
            mathOperationInProgress = savedInstanceState.getString("BUILD_TEXT_VIEW");
            buildTextViews.setText(mathOperationInProgress);
        }
    }

    private void onClickAdvanceCalculatorButtons() {
        operationList.forEach(advanceOperation -> advanceOperation.getOperationButton().setOnClickListener(v -> {
            switch(advanceOperation.getOperationButton().getText().toString()){
                case "sin":
                    inBuild("sin(");
                    break;
                case "cos":
                    inBuild("cos(");
                    break;
                case "tan":
                    inBuild("tan(");
                    break;
                case "ln":
                    inBuild("log(");
                    break;
                case "log":
                    inBuild("log10(");
                    break;
                case "()":
                    matchBrackets();
                    break;
                case "X^2":
                    inBuild("^2");
                    break;
                case "sqrt":
                    inBuild("sqrt(");
                    break;
                case "%":
                    inBuild("%");
                    break;
                case "X^Y":
                    inBuild("^");
                    break;
            }
        }));
    }

    private void matchBrackets() {
        long numberOfCloseBracket = mathOperationInProgress.chars().filter(ch -> ch == ')').count();
        long numberOfOpenBracket = mathOperationInProgress.chars().filter(ch -> ch == '(').count();
        if(numberOfOpenBracket>numberOfCloseBracket){
            inBuild(")");
        }
        else {
            inBuild("(");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("BUILD_TEXT_VIEW", mathOperationInProgress);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        mathOperationInProgress = savedInstanceState.getString("BUILD_TEXT_VIEW");
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initAdvanceOperation(){
        operationList.add(new AdvanceOperation("sin",findViewById(R.id.sin)));
        operationList.add(new AdvanceOperation("cos",findViewById(R.id.cos)));
        operationList.add(new AdvanceOperation("ln",findViewById(R.id.ln)));
        operationList.add(new AdvanceOperation("log",findViewById(R.id.log)));
        operationList.add(new AdvanceOperation("tan",findViewById(R.id.tan)));
        operationList.add(new AdvanceOperation("sqrt",findViewById(R.id.sqrt)));
        operationList.add(new AdvanceOperation("pow2",findViewById(R.id.pow2)));
        operationList.add(new AdvanceOperation("brackets",findViewById(R.id.brackets)));
        operationList.add(new AdvanceOperation("percent",findViewById(R.id.percent)));
        operationList.add(new AdvanceOperation("powY",findViewById(R.id.powY)));
    }
}