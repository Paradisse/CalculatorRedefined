package com.example.calculatorredefined.states;

import com.example.calculatorredefined.CalculatorModel;
import com.example.calculatorredefined.CalculatorViewModel;

import java.math.BigDecimal;

public class FirstOperandInputState implements IState {
    CalculatorViewModel calculatorViewModel;

    public FirstOperandInputState(CalculatorViewModel calculatorViewModel) {
        this.calculatorViewModel = calculatorViewModel;
    }

    @Override
    public void pressANumber(String numberPressed) {
        calculatorViewModel.setFirstOperandString(calculatorViewModel.getFirstOperandString() + numberPressed);
        calculatorViewModel.setFirstNumber(new BigDecimal(calculatorViewModel.getFirstOperandString()));

        calculatorViewModel.setLastSavedResult(calculatorViewModel.getFirstOperandString());
    }

    @Override
    public void pressASign() {
        if (!calculatorViewModel.getFirstOperandString().isEmpty()) {
            calculatorViewModel.setFirstOperandString(CalculatorModel.negate(calculatorViewModel.getFirstNumber()).toString());
            calculatorViewModel.setLastSavedResult(calculatorViewModel.getFirstOperandString());
        }
    }

    @Override
    public void pressADot() {
        if (!calculatorViewModel.isDotPressed()) {
            calculatorViewModel.setDotPressed();

            if (!calculatorViewModel.isFirstOperandStringEmpty()) {
                calculatorViewModel.setFirstOperandString(calculatorViewModel.getFirstOperandString() + '.');
            } else {
                calculatorViewModel.setFirstOperandString(calculatorViewModel.getFirstOperandString() + "0.");
            }

            calculatorViewModel.setFirstNumber(new BigDecimal(calculatorViewModel.getFirstOperandString()));

            calculatorViewModel.setLastSavedResult(calculatorViewModel.getFirstOperandString());
        }
    }

    @Override
    public void pressAllClear() {
        //calculatorViewModel.setState(calculatorViewModel.getFirstOperandInputState());
        calculatorViewModel.setDotUnpressed();
        calculatorViewModel.setCurrentOperation("");
        calculatorViewModel.setFirstOperandString("");
        calculatorViewModel.setSecondOperandString("");
        calculatorViewModel.setLastSavedResult("");
    }

    @Override
    public void pressClear() {
        if (!calculatorViewModel.isFirstOperandStringEmpty()) {
            if (calculatorViewModel.isLastCharacterOfFirstNumberADot())
                calculatorViewModel.setDotUnpressed();

            calculatorViewModel.removeLastCharacterOfFirstOperandString();
            calculatorViewModel.setFirstNumber(new BigDecimal(calculatorViewModel.getFirstOperandString()));

            calculatorViewModel.setLastSavedResult(calculatorViewModel.getFirstOperandString());
        }
    }

    @Override
    public void evaluateExpression() {
        calculatorViewModel.setLastSavedResult(calculatorViewModel.getFirstOperandString());
    }

    @Override
    public void pressAdd() {
        calculatorViewModel.setCurrentOperation("+");
        calculatorViewModel.setDotUnpressed();
        calculatorViewModel.setState(calculatorViewModel.getAddPressedState());
    }

    @Override
    public void pressSubtract() {
        calculatorViewModel.setCurrentOperation("-");
        calculatorViewModel.setDotUnpressed();
        calculatorViewModel.setState(calculatorViewModel.getSubtractPressedState());
    }

    @Override
    public void pressDivide() {
        calculatorViewModel.setCurrentOperation("÷");
        calculatorViewModel.setDotUnpressed();
        calculatorViewModel.setState(calculatorViewModel.getDividePressedState());
    }

    @Override
    public void pressRemain() {
        calculatorViewModel.setCurrentOperation("%");
        calculatorViewModel.setDotUnpressed();
        calculatorViewModel.setState(calculatorViewModel.getRemainPressedState());
    }

    @Override
    public void pressMultiply() {
        calculatorViewModel.setCurrentOperation("×");
        calculatorViewModel.setDotUnpressed();
        calculatorViewModel.setState(calculatorViewModel.getMultiplyPressedState());
    }
}
