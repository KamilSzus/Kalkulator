package com.example.kalkulator;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends SimplyCalculator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        initOperationButton();
    }

}