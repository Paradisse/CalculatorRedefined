package com.example.calculatorredefined;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.calculatorredefined.states.CalculatorStatesHolder;
import com.example.calculatorredefined.states.FirstOperandInputState;

public class CalculatorViewModel extends ViewModel {

    CalculatorStatesHolder statesHolder;

    public CalculatorViewModel() {
        statesHolder = new CalculatorStatesHolder();
    }

    public String changeExpression(String pressedNumber) {
        statesHolder.pressANumber(pressedNumber);
        return getExpression();
    }

    public String makeExpressionFractional() {
        statesHolder.pressADot();
        return getExpression();
    }

    public String removeOneCharacterFromExpression() {
        statesHolder.pressClear();
        return getExpression();
    }

    public String allClear() {
        statesHolder.pressAllClear();
        return getExpression();
    }

    public String changeSignOfANumber() {
        statesHolder.changeSign();
        return getExpression();
    }

    public String getExpressionAfterSubtractClicked() {
        statesHolder.pressSubtract();
        return getExpression();
    }

    public String getExpressionAfterAdditionClicked() {
        statesHolder.pressAdd();
        return getExpression();
    }

    public String getExpressionAfterDivideClicked() {
        statesHolder.pressDivide();
        return getExpression();
    }

    public String getExpressionAfterMultiplyClicked() {
        statesHolder.pressMultiply();
        return getExpression();
    }

    public String getExpressionAfterRemainderClicked() {
        statesHolder.pressRemain();
        return getExpression();
    }

    public String evaluateExpression() {
        statesHolder.evaluateExpression();
        return getExpression();
    }

    @NonNull
    public String getExpression() {
        return statesHolder.getFirstNumber() + statesHolder.getOperation() + statesHolder.getSecondNumber();
    }

    public String getExpressionResult() {
        return statesHolder.getLastResult();
    }

    public String clearResult() {
        statesHolder.clearResult();
        return statesHolder.getLastResult();
    }
}
