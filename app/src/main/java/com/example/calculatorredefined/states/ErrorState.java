package com.example.calculatorredefined.states;

import com.example.calculatorredefined.CalculatorModel;
import com.example.calculatorredefined.CalculatorViewModel;

import java.math.BigDecimal;

public class ErrorState implements IState {
    CalculatorViewModel calculatorViewModel;

    public ErrorState(CalculatorViewModel calculatorViewModel) {
        this.calculatorViewModel = calculatorViewModel;
    }

    @Override
    public void pressANumber(String numberPressed) {
        calculatorViewModel.setState(calculatorViewModel.getFirstOperandInputState());
        calculatorViewModel.setFirstOperandString(calculatorViewModel.getFirstOperandString() + numberPressed);
        calculatorViewModel.setFirstNumber(new BigDecimal(calculatorViewModel.getFirstOperandString()));

        BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
        BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
        BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);
        calculatorViewModel.setLastSavedResult(divisionResult.toString());
    }

    @Override
    public void pressASign() {}

    @Override
    public void pressADot() {
        calculatorViewModel.setState(calculatorViewModel.getFirstOperandInputState());

        calculatorViewModel.setFirstOperandString(calculatorViewModel.getFirstOperandString() + "0.");
        calculatorViewModel.setFirstNumber(new BigDecimal(calculatorViewModel.getFirstOperandString()));
        calculatorViewModel.setLastSavedResult(calculatorViewModel.getFirstOperandString());
    }

    @Override
    public void pressAllClear() {
        calculatorViewModel.setState(calculatorViewModel.getFirstOperandInputState());
        calculatorViewModel.setDotUnpressed();
        calculatorViewModel.setCurrentOperation("");
        calculatorViewModel.setFirstOperandString("");
        calculatorViewModel.setSecondOperandString("");
        calculatorViewModel.setLastSavedResult("");
    }

    @Override
    public void pressClear() {}

    @Override
    public void evaluateExpression() {}

    @Override
    public void pressAdd() {}

    @Override
    public void pressSubtract() {}

    @Override
    public void pressDivide() {}

    @Override
    public void pressRemain() {}

    @Override
    public void pressMultiply() {}
}
