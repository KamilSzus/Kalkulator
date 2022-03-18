package com.example.kalkulator;

import android.os.Bundle;

import androidx.annotation.NonNull;

public class MainActivity extends SimplyCalculator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildTextViews = findViewById(R.id.buildTextViews);

        initMenuButton();
        hideSystemBars();
        initNumbersButton();
        onClickNumbers();
        initSimplyOperation();
        onClickSimplyOperation();

        if(savedInstanceState!=null){
            mathOperationInProgress = savedInstanceState.getString("BUILD_TEXT_VIEW");
            buildTextViews.setText(mathOperationInProgress);
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
}