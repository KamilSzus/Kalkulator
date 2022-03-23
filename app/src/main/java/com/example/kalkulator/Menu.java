package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {

    List<MenuButtons> menuButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initMenuButtons();

        menuButtons.forEach(button -> button.getMenuButton().setOnClickListener(v -> {
                switch(button.getMenuButton().getText().toString()){
                    case "Prosty":
                        runNewMethodWithName(MainActivity.class);
                        break;
                    case "Zaawansowany":
                        runNewMethodWithName(AdvanceCalculator.class);
                        break;
                    case "O Aplikacji":
                        runNewMethodWithName(About.class);
                        break;
                    case "Powrót":
                        finish();
                        System.exit(0);
                        break;
                }
            }));
    }

    private void initMenuButtons() {
        menuButtons.add(new MenuButtons("Simply",findViewById(R.id.simply)));
        menuButtons.add(new MenuButtons("Advance",findViewById(R.id.advance)));
        menuButtons.add(new MenuButtons("About",findViewById(R.id.about)));
        menuButtons.add(new MenuButtons("Exit",findViewById(R.id.exit)));
    }

    private void runNewMethodWithName(Class<?> className){
        Intent intent = new Intent(this, className);
        startActivity(intent);
    }
}