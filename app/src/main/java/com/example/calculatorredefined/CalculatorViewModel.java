package com.example.calculatorredefined;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import java.math.BigDecimal;

public class CalculatorViewModel extends ViewModel {

    private enum State {
        FIRST_OPERAND_INPUT,
        ADD_PRESSED,
        SUBTRACT_PRESSED,
        DIVIDE_PRESSED,
        MULTIPLY_PRESSED,
        REMAINDER_PRESSED,
        EVAL_PRESSED,
        ERROR_STATE
    }

    private State state = State.FIRST_OPERAND_INPUT;
    private boolean isDotPressed = false;
    private final CalculatorModel calculatorModel = new CalculatorModel();
    private String firstOperandString = "";
    private String secondOperandString = "";
    private String currentOperation = "";
    private String lastSavedResult = "";
    private BigDecimal firstNumber, secondNumber;

    public String getAdditionResult() {
        changeCalculatorStateOnAddition();
        return getExpression();
    }

    public String getSubtractionResult() {
        changeCalculatorStateOnSubtraction();
        return getExpression();
    }

    public String getDivisionResult() {
        changeCalculatorStateOnDivision();

        if (state != State.ERROR_STATE)
            return getExpression();
        else
            return "Error";
    }

    public String getMultiplyResult() {
        changeCalculatorStateOnMultiply();
        return getExpression();
    }

    public String getRemainderResult() {
        changeCalculatorStateOnRemainder();
        return getExpression();
    }

    public String getExpression() {
        return firstOperandString + currentOperation + secondOperandString;
    }

    public String getExpressionResult() {
        return lastSavedResult;
    }

    public String clearResult() {
        return "";
    }

    public String clearExpression() {
        state = State.FIRST_OPERAND_INPUT;
        isDotPressed = false;
        firstOperandString = "";
        secondOperandString = "";
        currentOperation = "";
        lastSavedResult = "";

        return getExpression();
    }

    private void saveCurrentNumbers() {
        if (firstOperandString.length() > 0) {
            if (firstOperandString.equals("."))
                firstOperandString = '0' + firstOperandString;

            firstNumber = new BigDecimal(firstOperandString);
        }
        if (secondOperandString.length() > 0) {
            if (secondOperandString.equals("."))
                secondOperandString = '0' + secondOperandString;

            secondNumber = new BigDecimal(secondOperandString);
        }
    }

    public String changeExpression(String number) {
        if (state == State.ERROR_STATE) {
            state = getStateByOperand();
        }

        if (isItAFirstNumber()) {
            firstOperandString += number;
        } else {
            secondOperandString += number;
        }
        saveCurrentNumbers();

        return getExpression();
    }

    public String changeExpressionResult() {
        try {
            if (isItAFirstNumber() || secondOperandString.length() == 0) {
                lastSavedResult = firstOperandString;
            } else {
                lastSavedResult = getOperationResult();
            }

            return lastSavedResult;
        } catch (ArithmeticException e) {
            state = State.ERROR_STATE;
            return "Error";
        }
    }

    public String makeExpressionFractional() {
        if (!isDotPressed) {
            isDotPressed = true;
            if (isItAFirstNumber())
                firstOperandString += '.';
            else
                secondOperandString += '.';

            saveCurrentNumbers();
        }

        return getExpression();
    }

    public String clearEntireExpression() {
        return clearExpression();
    }

    public String eraseOneCharFromExpression(@NonNull String expressionText) {
        if (expressionText.length() > 0) {
            if (isLastCharacterADot(expressionText)) {
                isDotPressed = false;
            }

            if (isItAFirstNumber()) {
                firstOperandString = removeLastCharacter(firstOperandString);
            } else if (isLastCharacterAnOperation(expressionText)) {
                currentOperation = "";
                state = State.FIRST_OPERAND_INPUT;
            } else if (secondOperandString.length() > 0){
                secondOperandString = removeLastCharacter(secondOperandString);
                if (secondOperandString.equals("0")) {
                    state = State.ERROR_STATE;
                } else if (secondOperandString.length() == 0 && state == State.ERROR_STATE)
                    state = getStateByOperand();
            }
            saveCurrentNumbers();
        }

        return getExpression();
    }

    private State getStateByOperand() {
        switch (currentOperation) {
            case "÷":
                return State.DIVIDE_PRESSED;
            case "×":
                return State.MULTIPLY_PRESSED;
            case "+":
                return State.ADD_PRESSED;
            case "-":
                return State.SUBTRACT_PRESSED;
            case "%":
                return State.REMAINDER_PRESSED;
            default:
                return null;
        }
    }

    public void changeCalculatorStateOnAddition() {
        if (state != State.ERROR_STATE) {
            if (isItAFirstNumber() || secondOperandString.length() == 0) {
                isDotPressed = false;
            } else {
                firstOperandString = getOperationResult();
                saveCurrentNumbers();
            }

            state = State.ADD_PRESSED;
            secondOperandString = "";
            saveCurrentNumbers();
            currentOperation = "+";
        }
    }

    public void changeCalculatorStateOnSubtraction() {
        if (state != State.ERROR_STATE) {
            if (isItAFirstNumber() || secondOperandString.length() == 0) {
                isDotPressed = false;
            } else {
                firstOperandString = getOperationResult();
                saveCurrentNumbers();
            }

            state = State.SUBTRACT_PRESSED;
            secondOperandString = "";
            saveCurrentNumbers();
            currentOperation = "-";
        }
    }

    public void changeCalculatorStateOnDivision() {
        if (state != State.ERROR_STATE) {
            try {
                if (isItAFirstNumber() || secondOperandString.length() == 0) {
                    isDotPressed = false;
                } else {
                    firstOperandString = getOperationResult();
                    isDotPressed = true;
                    saveCurrentNumbers();
                }

                state = State.DIVIDE_PRESSED;
                secondOperandString = "";
                saveCurrentNumbers();
                currentOperation = "÷";
            } catch (ArithmeticException e) {
                clearExpression();
                state = State.ERROR_STATE;
            }
        }
    }

    public void changeCalculatorStateOnMultiply() {
        if (state != State.ERROR_STATE) {
            if (isItAFirstNumber() || secondOperandString.length() == 0) {
                isDotPressed = false;
            } else {
                firstOperandString = getOperationResult();
                saveCurrentNumbers();
            }

            state = State.MULTIPLY_PRESSED;
            secondOperandString = "";
            saveCurrentNumbers();
            currentOperation = "×";
        }
    }

    public void changeCalculatorStateOnRemainder() {
        if (state != State.ERROR_STATE) {
            if (isItAFirstNumber() || secondOperandString.length() == 0) {
                isDotPressed = false;
            } else {
                firstOperandString = getOperationResult();
                saveCurrentNumbers();
            }

            state = State.REMAINDER_PRESSED;
            secondOperandString = "";
            saveCurrentNumbers();
            currentOperation = "%";
        }
    }

    public String negateExpression() {
        if (state != State.ERROR_STATE) {
            if (isItAFirstNumber() && !firstOperandString.isEmpty()) {
                firstOperandString = calculatorModel.negate(firstNumber).toString();
            } else if (!secondOperandString.isEmpty()) {
                secondOperandString = calculatorModel.negate(secondNumber).toString();
            }

            saveCurrentNumbers();
        }

        return getExpression();
    }

    public String evaluateExpression() {
        if (state != State.ERROR_STATE) {
            String evaluateResult;

            try {
                if (isItAFirstNumber()) {
                    evaluateResult = firstOperandString;
                } else {
                    evaluateResult = getOperationResult();
                }
            } catch (ArithmeticException e) {
                state = State.ERROR_STATE;
                return "Error";
            }

            state = State.FIRST_OPERAND_INPUT;

            firstOperandString = evaluateResult;
            secondOperandString = "";
            currentOperation = "";
            saveCurrentNumbers();

            return evaluateResult;
        }
        else
            return null;
    }

    private boolean isItAFirstNumber() {
        return state == State.FIRST_OPERAND_INPUT || state == State.EVAL_PRESSED;
    }

    @NonNull
    private String removeLastCharacter(@NonNull String expressionText) {
        return expressionText.substring(0, expressionText.length() - 1);
    }

    private boolean isLastCharacterADot(@NonNull String expressionText) {
        return expressionText.endsWith(".");
    }

    private boolean isLastCharacterAnOperation(@NonNull String expressionText) {
        return expressionText.endsWith("+") || expressionText.endsWith("-") || expressionText.endsWith("÷") ||
                expressionText.endsWith("×") || expressionText.endsWith("%");
    }

    @NonNull
    private String getOperationResult() {
        isDotPressed = false;

        if (state == State.ADD_PRESSED)
            return calculatorModel.add(firstNumber, secondNumber).toString();
        else if (state == State.SUBTRACT_PRESSED)
            return calculatorModel.subtract(firstNumber, secondNumber).toString();
        else if (state == State.DIVIDE_PRESSED)
            return calculatorModel.divide(firstNumber, secondNumber).toString();
        else if (state == State.REMAINDER_PRESSED)
            return calculatorModel.remainder(firstNumber, secondNumber).toString();
        else if (state == State.MULTIPLY_PRESSED)
            return calculatorModel.multiply(firstNumber, secondNumber).toString();
        else
            return  "";
    }
}
