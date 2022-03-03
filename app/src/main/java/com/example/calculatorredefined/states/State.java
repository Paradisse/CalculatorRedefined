package com.example.calculatorredefined.states;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

public abstract class State {
    protected BigDecimal firstNumber, secondNumber, lastResult;
    protected String firstNumberString = "";
    protected String secondNumberString = "";
    protected String lastResultString = "";
    protected boolean isDotPressed = false;
    protected String currentOperation = "";
    protected CalculatorStatesHolder calculatorStatesHolder;

    public State(@NonNull State state) {
        this.currentOperation = state.currentOperation;
        this.firstNumber = state.firstNumber;
        this.secondNumber = state.secondNumber;
        this.isDotPressed = state.isDotPressed;
        this.lastResult = state.lastResult;
        this.firstNumberString = state.firstNumberString;
        this.secondNumberString = state.secondNumberString;
        this.lastResultString = state.lastResultString;
        this.calculatorStatesHolder = state.calculatorStatesHolder;
    }

    public State(CalculatorStatesHolder calculatorStatesHolder) {
        this.calculatorStatesHolder = calculatorStatesHolder;
    }

    public State() {}

    public abstract void pressANumber(String numberPressed);

    public abstract void changeSign();

    public abstract void pressADot();

    public abstract void pressAllClear();

    public abstract void pressClear();

    public abstract void evaluateExpression();

    public abstract void pressAdd();

    public abstract void pressSubtract();

    public abstract void pressDivide();

    public abstract void pressRemain();

    public abstract void pressMultiply();

    protected boolean doesItHaveOneDecimalAfterPoint(@NonNull String number) {
        return number.substring(number.indexOf(".")).length() == 1;
    }

    protected String removeLastCharacter(@NonNull String number) {
        if (!number.isEmpty())
            return number.substring(0, number.length() - 1);

        return null;
    }
}
