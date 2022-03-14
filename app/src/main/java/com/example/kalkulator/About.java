package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class About extends AppCompatActivity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        backButton = findViewById(R.id.back);
        backButton.setOnClickListener(v ->runNewMethodWithName());
    }
    private void runNewMethodWithName(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}