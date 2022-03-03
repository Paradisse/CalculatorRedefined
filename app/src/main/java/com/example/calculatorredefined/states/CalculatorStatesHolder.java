package com.example.calculatorredefined.states;

public class CalculatorStatesHolder {
    State state;

    public CalculatorStatesHolder() {
        this.state = new FirstOperandInputState(this);
    }

    public void changeState(State state) {
        this.state = state;
    }

    public String getFirstNumber() {
        return state.firstNumberString;
    }

    public String getSecondNumber() {
        return state.secondNumberString;
    }

    public String getLastResult() {
        return state.lastResultString;
    }

    public String getOperation() {
        return state.currentOperation;
    }

    public void pressANumber(String pressedNumber) {
        state.pressANumber(pressedNumber);
    }

    public void pressADot() {
        state.pressADot();
    }

    public void pressClear() {
        state.pressClear();
    }

    public void pressAllClear() {
        state.pressAllClear();
    }

    public void changeSign() {
        state.changeSign();
    }

    public void pressSubtract() {
        state.pressSubtract();
    }

    public void pressAdd() {
        state.pressAdd();
    }

    public void pressDivide() {
        state.pressDivide();
    }

    public void pressMultiply() {
        state.pressMultiply();
    }

    public void pressRemain() {
        state.pressRemain();
    }

    public void evaluateExpression() {
        state.evaluateExpression();
    }

    public void clearResult() {
        state.lastResultString = "";
        state.lastResult = null;
    }
}
