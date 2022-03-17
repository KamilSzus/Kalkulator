package com.example.kalkulator;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class AdvanceCalculator extends SimplyCalculator {

    List<AdvanceOperation> operationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_calculator);
        buildTextViews = findViewById(R.id.buildTextViews);
        backToMenu = findViewById(R.id.menu);
        backToMenu.setOnClickListener(v->{
            Intent intent = new Intent(this, Menu.class);
            startActivity(intent);
        });

        hideSystemBars();
        initNumbersButton();
        onClickNumbers();
        initSimplyOperation();
        onClickSimplyOperation();
        initAdvanceOperation();

        operationList.forEach(advanceOperation -> advanceOperation.getOperationButton().setOnClickListener(v -> {
            switch(v.getId()){
                case R.id.sin:
                    inBuild("Sin(");
                    break;
                case R.id.cos:
                    inBuild("Cos(");
                    break;
                case R.id.tan:
                    inBuild("Tan(");
                    break;
                case R.id.ln:
                    inBuild("Ln(");
                    break;
                case R.id.log:
                    inBuild("Log(");
                    break;
                case R.id.brackets:
                    inBuild(")");
                    break;
                case R.id.pow2:
                    inBuild("Pow(");
                    break;
            }
        }));
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
    }
}