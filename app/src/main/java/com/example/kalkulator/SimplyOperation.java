package com.example.kalkulator;

import android.widget.Button;

public class SimplyOperation {
    private final String operation;
    private final Button operationButton;

    public SimplyOperation(String operation, Button operationButton) {
        this.operation = operation;
        this.operationButton = operationButton;
    }

    public String getOperation() {
        return operation;
    }

    public Button getOperationButton() {
        return operationButton;
    }
}
