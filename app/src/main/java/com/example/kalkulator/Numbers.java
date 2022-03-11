package com.example.kalkulator;

import android.widget.Button;

public class Numbers {
    private final String number;
    private final Button numberButton;


    public Numbers(String number, Button numberButtonId) {
        this.number = number;
        this.numberButton = numberButtonId;
    }

    public String getNumber() {
        return number;
    }

    public Button getNumberButtonId() {
        return numberButton;
    }
}
