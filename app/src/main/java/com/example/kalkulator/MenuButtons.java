package com.example.kalkulator;

import android.widget.Button;

public class MenuButtons {
    private final String nameButton;
    private final Button menuButton;

    public MenuButtons(String nameButton, Button menuButton) {
        this.nameButton = nameButton;
        this.menuButton = menuButton;
    }

    public String getNameButton() {
        return nameButton;
    }

    public Button getMenuButton() {
        return menuButton;
    }
}
