package com.example.calculatorredefined;

import androidx.lifecycle.ViewModel;

import com.example.calculatorredefined.states.AddPressedState;
import com.example.calculatorredefined.states.DividePressedState;
import com.example.calculatorredefined.states.ErrorState;
import com.example.calculatorredefined.states.FirstOperandInputState;
import com.example.calculatorredefined.states.IState;
import com.example.calculatorredefined.states.MultiplyPressedState;
import com.example.calculatorredefined.states.RemainderPressedState;
import com.example.calculatorredefined.states.SubtractPressedState;

import java.math.BigDecimal;

public class CalculatorViewModel extends ViewModel {
    IState firstOperandInputState;
    IState addPressedState;
    IState subtractPressedState;
    IState dividePressedState;
    IState remainPressedState;
    IState multiplyPressedState;
    IState errorState;

    IState state;
    private boolean isDotPressed = false;
    private String firstOperandString = "";
    private String secondOperandString = "";
    private String currentOperation = "";
    private String lastSavedResult = "";
    private BigDecimal firstNumber, secondNumber;

    public CalculatorViewModel() {
        firstOperandInputState = new FirstOperandInputState(this);
        addPressedState = new AddPressedState(this);
        subtractPressedState = new SubtractPressedState(this);
        dividePressedState = new DividePressedState(this);
        remainPressedState = new RemainderPressedState(this);
        multiplyPressedState = new MultiplyPressedState(this);
        errorState = new ErrorState(this);

        state = firstOperandInputState;
    }

    public String changeExpression(String pressedNumber) {
        state.pressANumber(pressedNumber);
        return getExpression();
    }

    public String makeExpressionFractional() {
        state.pressADot();
        return getExpression();
    }

    public String removeOneCharacterFromExpression() {
        state.pressClear();
        return getExpression();
    }

    public String allClear() {
        state.pressAllClear();
        return getExpression();
    }

    public String clearResult() {
        lastSavedResult = "";
        return getExpressionResult();
    }

    public String changeSignOfANumber() {
        state.pressASign();
        return getExpression();
    }

    public String getExpressionAfterSubtractClicked() {
        state.pressSubtract();
        return getExpression();
    }

    public String getExpressionAfterAdditionClicked() {
        state.pressAdd();
        return getExpression();
    }

    public String getExpressionAfterDivideClicked() {
        state.pressDivide();
        return getExpression();
    }

    public String getExpressionAfterMultiplyClicked() {
        state.pressMultiply();
        return getExpression();
    }

    public String getExpressionAfterRemainderClicked() {
        state.pressRemain();
        return getExpression();
    }

    public String evaluateExpression() {
        state.evaluateExpression();
        return getExpression();
    }

    public String getExpression() {
        return firstOperandString + currentOperation + secondOperandString;
    }

    public String getExpressionResult() {
        return lastSavedResult;
    }

    public String getFirstOperandString() {
        return firstOperandString;
    }

    public void setFirstOperandString(String firstOperandString) {
        this.firstOperandString = firstOperandString;
    }

    public boolean isLastCharacterOfFirstNumberADot() {
        return firstOperandString.endsWith(".");
    }

    public boolean isLastCharacterOfSecondNumberADot() {
        return secondOperandString.endsWith(".");
    }

    public boolean isFirstOperandStringEmpty() {
        return firstOperandString.isEmpty();
    }

    public boolean isSecondOperandStringEmpty() {
        return secondOperandString.isEmpty();
    }

    public void removeLastCharacterOfFirstOperandString() {
        firstOperandString = firstOperandString.substring(0, firstOperandString.length() - 1);
    }

    public void removeLastCharacterOfSecondOperandString() {
        secondOperandString = secondOperandString.substring(0, secondOperandString.length() - 1);
    }

    public String getSecondOperandString() {
        return secondOperandString;
    }

    public void setSecondOperandString(String secondOperandString) {
        this.secondOperandString = secondOperandString;
    }

    public String getLastSavedResult() {
        return lastSavedResult;
    }

    public void setLastSavedResult(String lastSavedResult) {
        this.lastSavedResult = lastSavedResult;
    }

    public String getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(String currentOperation) {
        this.currentOperation = currentOperation;
    }

    public boolean isOperationEmpty() {
        return currentOperation.isEmpty();
    }

    public IState getState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(BigDecimal firstNumber) {
        this.firstNumber = firstNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }

    public boolean isDotPressed() {
        return isDotPressed;
    }

    public void setDotPressed() {
        isDotPressed = true;
    }

    public void setDotUnpressed() {
        isDotPressed = false;
    }

    public IState getAddPressedState() {
        return addPressedState;
    }

    public IState getDividePressedState() {
        return dividePressedState;
    }

    public IState getFirstOperandInputState() {
        return firstOperandInputState;
    }

    public IState getMultiplyPressedState() {
        return multiplyPressedState;
    }

    public IState getRemainPressedState() {
        return remainPressedState;
    }

    public IState getSubtractPressedState() {
        return subtractPressedState;
    }

    public IState getErrorState() {
        return errorState;
    }

    /*private enum State {
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

    public String getExpressionAfterSubtract() {
        changeCalculatorStateOnSubtraction();
        return getExpression();
    }

    public String getExpressionAfterDivision() {
        changeCalculatorStateOnDivision();

        if (state != State.ERROR_STATE)
            return getExpression();
        else {
            lastSavedResult = "Error";
            return lastSavedResult;
        }
    }

    public String getExpressionAfterMultiply() {
        changeCalculatorStateOnMultiply();
        return getExpression();
    }

    public String getExpressionAfterRemainder() {
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
            lastSavedResult = "Error";
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
                } else if (secondOperandString.length() == 0 && state == State.ERROR_STATE) {
                    state = getStateByOperand();
                }
            }

            if (isItASign(firstOperandString))
                firstOperandString = "";
            else if (isItASign(secondOperandString))
                secondOperandString = "";

            saveCurrentNumbers();
        }

        return getExpression();
    }

    private boolean isItASign(@NonNull String expressionText) {
        return expressionText.endsWith("-");
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
                lastSavedResult = "Error";
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
    }*/
}
