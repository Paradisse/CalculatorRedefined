package com.example.calculatorredefined.states;

public interface IState {
    void pressANumber(String numberPressed);
    void pressASign();
    void pressADot();
    void pressAllClear();
    void pressClear();
    void evaluateExpression();
    void pressAdd();
    void pressSubtract();
    void pressDivide();
    void pressRemain();
    void pressMultiply();
}
