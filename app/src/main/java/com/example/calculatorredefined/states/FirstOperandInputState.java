package com.example.calculatorredefined.states;

import com.example.calculatorredefined.CalculatorModel;

import java.math.BigDecimal;

public class FirstOperandInputState extends State {
    FirstOperandInputState(CalculatorStatesHolder calculatorStatesHolder) {
        super(calculatorStatesHolder);
    }

    public FirstOperandInputState(State state) {
        super(state);
    }

    @Override
    public void pressANumber(String pressedNumber) {
        firstNumberString += pressedNumber;
        firstNumber = new BigDecimal(firstNumberString);
    }

    @Override
    public void changeSign() {
        if (!firstNumberString.isEmpty()) {
            firstNumber = CalculatorModel.negate(firstNumber);
            firstNumberString = firstNumber.toString();
        }
    }

    @Override
    public void pressADot() {
        if (!isDotPressed) {
            isDotPressed = true;

            if (firstNumberString.isEmpty())
                firstNumberString += '0';
            firstNumberString += '.';
        }
    }

    @Override
    public void pressAllClear() {
        isDotPressed = false;
        firstNumber = null;
        secondNumber = null;
        firstNumberString = "";
        secondNumberString = "";
        lastResult = null;
        lastResultString = "";
        currentOperation = "";
    }

    @Override
    public void pressClear() {
        if (firstNumberString.length() > 1) {
            if (firstNumberString.endsWith("."))
                isDotPressed = false;

            firstNumberString = removeLastCharacter(firstNumberString);

            if (firstNumberString.equals("-"))
                firstNumberString = removeLastCharacter(firstNumberString);
            else
                firstNumber = new BigDecimal(firstNumberString);
        } else if (!firstNumberString.isEmpty()){
            firstNumber = null;
            firstNumberString = "";
        }
    }

    @Override
    public void evaluateExpression() {
        lastResult = firstNumber;
        lastResultString = firstNumberString;
        currentOperation = "";
    }

    @Override
    public void pressAdd() {
        if (!firstNumberString.isEmpty()) {
            currentOperation = "+";
            isDotPressed = false;
            calculatorStatesHolder.changeState(new AddPressedState(this));
        }
    }

    @Override
    public void pressSubtract() {
        if (!firstNumberString.isEmpty()) {
            currentOperation = "-";
            isDotPressed = false;
            calculatorStatesHolder.changeState(new SubtractPressedState(this));
        }
    }

    @Override
    public void pressDivide() {
        if (!firstNumberString.isEmpty()) {
            currentOperation = "÷";
            isDotPressed = false;
            calculatorStatesHolder.changeState(new DividePressedState(this));
        }
    }

    @Override
    public void pressRemain() {
        if (!firstNumberString.isEmpty()) {
            currentOperation = "%";
            isDotPressed = false;
            calculatorStatesHolder.changeState(new RemainderPressedState(this));
        }
    }

    @Override
    public void pressMultiply() {
        if (!firstNumberString.isEmpty()) {
            currentOperation = "×";
            isDotPressed = false;
            calculatorStatesHolder.changeState(new MultiplyPressedState(this));
        }
    }
}
