package com.example.calculatorredefined.states;

import com.example.calculatorredefined.CalculatorModel;

import java.math.BigDecimal;

public class RemainderPressedState extends State {
    public RemainderPressedState(State state) {
        super(state);
    }

    @Override
    public void pressANumber(String pressedNumber) {
        secondNumberString += pressedNumber;
        secondNumber = new BigDecimal(secondNumberString);
    }

    @Override
    public void changeSign() {
        if (secondNumber != null) {
            secondNumber = CalculatorModel.negate(secondNumber);
            secondNumberString = secondNumber.toString();
        }
    }

    @Override
    public void pressADot() {
        if (!isDotPressed) {
            isDotPressed = true;

            if (secondNumberString.isEmpty())
                secondNumberString += '0';
            secondNumberString += '.';
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

        calculatorStatesHolder.changeState(new FirstOperandInputState(this));
    }

    @Override
    public void pressClear() {
        if (secondNumberString.length() > 1) {
            if (secondNumberString.endsWith("."))
                isDotPressed = false;

            secondNumberString = removeLastCharacter(secondNumberString);
            if (secondNumberString.equals("-"))
                secondNumberString = removeLastCharacter(secondNumberString);
            else
                secondNumber = new BigDecimal(secondNumberString);
        } else if (!secondNumberString.isEmpty()) {
            secondNumber = null;
            secondNumberString = "";
        } else {
            currentOperation = "";
            calculatorStatesHolder.changeState(new FirstOperandInputState(this));
        }
    }

    @Override
    public void evaluateExpression() {
        if (!secondNumberString.isEmpty()) {
            BigDecimal remainResult = CalculatorModel.remainder(firstNumber, secondNumber);

            firstNumber = remainResult;
            firstNumberString = remainResult.toString();
            secondNumber = null;
            secondNumberString = "";
            lastResult = remainResult;
            lastResultString = remainResult.toString();
            currentOperation = "";

            calculatorStatesHolder.changeState(new FirstOperandInputState(this));
        }
    }

    @Override
    public void pressAdd() {
        if (!secondNumberString.isEmpty()) {
            BigDecimal remainResult = CalculatorModel.remainder(firstNumber, secondNumber);

            firstNumber = remainResult;
            firstNumberString = remainResult.toString();
            secondNumber = null;
            secondNumberString = "";
            lastResult = remainResult;
            lastResultString = remainResult.toString();
            currentOperation = "+";
            isDotPressed = false;

            calculatorStatesHolder.changeState(new AddPressedState(this));
        } else {
            currentOperation = "+";
            calculatorStatesHolder.changeState(new AddPressedState(this));
        }
    }

    @Override
    public void pressSubtract() {
        if (!secondNumberString.isEmpty()) {
            BigDecimal remainResult = CalculatorModel.remainder(firstNumber, secondNumber);

            firstNumber = remainResult;
            firstNumberString = remainResult.toString();
            secondNumber = null;
            secondNumberString = "";
            lastResult = remainResult;
            lastResultString = remainResult.toString();
            currentOperation = "-";
            isDotPressed = false;

            calculatorStatesHolder.changeState(new SubtractPressedState(this));
        } else {
            currentOperation = "-";
            calculatorStatesHolder.changeState(new SubtractPressedState(this));
        }
    }

    @Override
    public void pressDivide() {
        if (!secondNumberString.isEmpty()) {
            BigDecimal remainResult = CalculatorModel.remainder(firstNumber, secondNumber);

            firstNumber = remainResult;
            firstNumberString = remainResult.toString();
            secondNumber = null;
            secondNumberString = "";
            lastResult = remainResult;
            lastResultString = remainResult.toString();
            currentOperation = "÷";
            isDotPressed = false;

            calculatorStatesHolder.changeState(new DividePressedState(this));
        } else {
            currentOperation = "÷";
            calculatorStatesHolder.changeState(new DividePressedState(this));
        }
    }

    @Override
    public void pressRemain() {
        if (!secondNumberString.isEmpty()) {
            BigDecimal remainResult = CalculatorModel.remainder(firstNumber, secondNumber);

            firstNumber = remainResult;
            firstNumberString = remainResult.toString();
            secondNumber = null;
            secondNumberString = "";
            lastResult = remainResult;
            lastResultString = remainResult.toString();
            currentOperation = "%";
            isDotPressed = false;
        }
    }

    @Override
    public void pressMultiply() {
        if (!secondNumberString.isEmpty()) {
            BigDecimal remainResult = CalculatorModel.remainder(firstNumber, secondNumber);

            firstNumber = remainResult;
            firstNumberString = remainResult.toString();
            secondNumber = null;
            secondNumberString = "";
            lastResult = remainResult;
            lastResultString = remainResult.toString();
            currentOperation = "×";
            isDotPressed = false;

            calculatorStatesHolder.changeState(new MultiplyPressedState(this));
        } else {
            currentOperation = "×";
            calculatorStatesHolder.changeState(new MultiplyPressedState(this));
        }
    }
}
