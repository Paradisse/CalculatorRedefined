package com.example.calculatorredefined.states;

import com.example.calculatorredefined.CalculatorModel;

import java.math.BigDecimal;

public class ErrorState extends State {
    public ErrorState(State state) {
        super(state);
    }

    @Override
    public void pressANumber(String numberPressed) {
        secondNumberString += numberPressed;
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
            calculatorStatesHolder.changeState(new DividePressedState(this));
        } else {
            currentOperation = "";
            calculatorStatesHolder.changeState(new FirstOperandInputState(this));
        }
    }

    @Override
    public void evaluateExpression() {
        try {
            BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

            firstNumber = divisionResult;
            firstNumberString = divisionResult.toString();
            secondNumber = null;
            secondNumberString = "";
            lastResult = divisionResult;
            lastResultString = divisionResult.toString();
            currentOperation = "";

            calculatorStatesHolder.changeState(new FirstOperandInputState(this));
        } catch (ArithmeticException e) {
            lastResult = null;
            lastResultString = "Error";
        }
    }

    @Override
    public void pressAdd() {
        if (!secondNumberString.isEmpty()) {
            try {
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                firstNumber = divisionResult;
                firstNumberString = divisionResult.toString();
                secondNumber = null;
                secondNumberString = "";
                lastResult = divisionResult;
                lastResultString = divisionResult.toString();
                currentOperation = "+";
                isDotPressed = false;

                calculatorStatesHolder.changeState(new AddPressedState(this));
            } catch (ArithmeticException e) {
                lastResult = null;
                lastResultString = "Error";
            }
        } else {
            currentOperation = "+";
        }
    }

    @Override
    public void pressSubtract() {
        if (!secondNumberString.isEmpty()) {
            try {
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                firstNumber = divisionResult;
                firstNumberString = divisionResult.toString();
                secondNumber = null;
                secondNumberString = "";
                lastResult = divisionResult;
                lastResultString = divisionResult.toString();
                currentOperation = "-";
                isDotPressed = false;

                calculatorStatesHolder.changeState(new SubtractPressedState(this));
            } catch (ArithmeticException e) {
                lastResult = null;
                lastResultString = "Error";
            }
        } else {
            currentOperation = "-";
        }
    }

    @Override
    public void pressDivide() {
        if (!secondNumberString.isEmpty()) {
            try {
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                firstNumber = divisionResult;
                firstNumberString = divisionResult.toString();
                secondNumber = null;
                secondNumberString = "";
                lastResult = divisionResult;
                lastResultString = divisionResult.toString();
                currentOperation = "÷";
                isDotPressed = false;
            } catch (ArithmeticException e) {
                lastResult = null;
                lastResultString = "Error";
            }
        }
    }

    @Override
    public void pressRemain() {
        if (!secondNumberString.isEmpty()) {
            try {
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                firstNumber = divisionResult;
                firstNumberString = divisionResult.toString();
                secondNumber = null;
                secondNumberString = "";
                lastResult = divisionResult;
                lastResultString = divisionResult.toString();
                currentOperation = "%";
                isDotPressed = false;

                calculatorStatesHolder.changeState(new RemainderPressedState(this));
            } catch (ArithmeticException e) {
                lastResult = null;
                lastResultString = "Error";
            }
        } else {
            currentOperation = "%";
            calculatorStatesHolder.changeState(new RemainderPressedState(this));
        }
    }

    @Override
    public void pressMultiply() {
        if (!secondNumberString.isEmpty()) {
            try {
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                firstNumber = divisionResult;
                firstNumberString = divisionResult.toString();
                secondNumber = null;
                secondNumberString = "";
                lastResult = divisionResult;
                lastResultString = divisionResult.toString();
                currentOperation = "×";
                isDotPressed = false;

                calculatorStatesHolder.changeState(new MultiplyPressedState(this));
            } catch (ArithmeticException e) {
                lastResult = null;
                lastResultString = "Error";
            }
        } else {
            currentOperation = "×";
            calculatorStatesHolder.changeState(new MultiplyPressedState(this));
        }
    }
}
