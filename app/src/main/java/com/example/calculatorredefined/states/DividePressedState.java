package com.example.calculatorredefined.states;

import com.example.calculatorredefined.CalculatorModel;
import com.example.calculatorredefined.CalculatorViewModel;

import java.math.BigDecimal;

public class DividePressedState implements IState {
    CalculatorViewModel calculatorViewModel;

    public DividePressedState(CalculatorViewModel calculatorViewModel) {
        this.calculatorViewModel = calculatorViewModel;
    }

    @Override
    public void pressANumber(String numberPressed) {
        calculatorViewModel.setSecondOperandString(calculatorViewModel.getSecondOperandString() + numberPressed);
        calculatorViewModel.setSecondNumber(new BigDecimal(calculatorViewModel.getSecondOperandString()));

        BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
        BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
        BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);
        calculatorViewModel.setLastSavedResult(divisionResult.toString());
    }

    @Override
    public void pressASign() {
        if (!calculatorViewModel.getSecondOperandString().isEmpty()) {
            BigDecimal negatedNumber = CalculatorModel.negate(calculatorViewModel.getSecondNumber());
            calculatorViewModel.setSecondOperandString(negatedNumber.toString());
            calculatorViewModel.setSecondNumber(negatedNumber);

            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);
            calculatorViewModel.setLastSavedResult(divisionResult.toString());
        }
    }

    @Override
    public void pressADot() {
        if (!calculatorViewModel.isDotPressed()) {
            calculatorViewModel.setDotPressed();

            if (!calculatorViewModel.isSecondOperandStringEmpty()) {
                calculatorViewModel.setSecondOperandString(calculatorViewModel.getSecondOperandString() + '.');
            } else {
                calculatorViewModel.setSecondOperandString(calculatorViewModel.getSecondOperandString() + "0.");
            }

            calculatorViewModel.setSecondNumber(new BigDecimal(calculatorViewModel.getSecondOperandString()));

            BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
            BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
            BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);
            calculatorViewModel.setLastSavedResult(divisionResult.toString());
        }
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
    public void pressClear() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            if (calculatorViewModel.isLastCharacterOfSecondNumberADot())
                calculatorViewModel.setDotUnpressed();

            calculatorViewModel.removeLastCharacterOfSecondOperandString();

            try {
                if (!calculatorViewModel.isSecondOperandStringEmpty()) {
                    calculatorViewModel.setSecondNumber(new BigDecimal(calculatorViewModel.getSecondOperandString()));

                    BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
                    BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
                    BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);
                    calculatorViewModel.setLastSavedResult(divisionResult.toString());

                } else {
                    calculatorViewModel.setLastSavedResult(calculatorViewModel.getFirstOperandString());
                }
            } catch (ArithmeticException e) {
                pressAllClear();
                calculatorViewModel.setState(calculatorViewModel.getErrorState());
                calculatorViewModel.setLastSavedResult("Error");
            }
        } else if (!calculatorViewModel.isOperationEmpty()) {
            calculatorViewModel.setCurrentOperation("");
            calculatorViewModel.setState(calculatorViewModel.getFirstOperandInputState());
        }
    }

    @Override
    public void evaluateExpression() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            try {
                calculatorViewModel.setState(calculatorViewModel.getFirstOperandInputState());

                BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
                BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);
                calculatorViewModel.setSecondOperandString("");
                calculatorViewModel.setCurrentOperation("");
                calculatorViewModel.setLastSavedResult(divisionResult.toString());
                calculatorViewModel.setFirstOperandString(divisionResult.toString());
                calculatorViewModel.setFirstNumber(divisionResult);
                calculatorViewModel.setLastSavedResult(divisionResult.toString());
            } catch (ArithmeticException e) {
                pressAllClear();
                calculatorViewModel.setState(calculatorViewModel.getErrorState());
                calculatorViewModel.setLastSavedResult("Error");
            }
        }
    }

    @Override
    public void pressAdd() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            try {
                BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
                BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                calculatorViewModel.setLastSavedResult(divisionResult.toString());
                calculatorViewModel.setFirstOperandString(divisionResult.toString());
                calculatorViewModel.setFirstNumber(divisionResult);
                calculatorViewModel.setSecondOperandString("");
                calculatorViewModel.setCurrentOperation("+");
                calculatorViewModel.setDotUnpressed();
            } catch (ArithmeticException e) {
                pressAllClear();
                calculatorViewModel.setState(calculatorViewModel.getErrorState());
                calculatorViewModel.setLastSavedResult("Error");
            }
        } else {
            calculatorViewModel.setCurrentOperation("+");
        }
    }

    @Override
    public void pressSubtract() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            try {
                BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
                BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                calculatorViewModel.setLastSavedResult(divisionResult.toString());
                calculatorViewModel.setFirstOperandString(divisionResult.toString());
                calculatorViewModel.setFirstNumber(divisionResult);
                calculatorViewModel.setSecondOperandString("");
                calculatorViewModel.setCurrentOperation("-");
                calculatorViewModel.setDotUnpressed();
                calculatorViewModel.setState(calculatorViewModel.getSubtractPressedState());
            } catch (ArithmeticException e) {
                pressAllClear();
                calculatorViewModel.setState(calculatorViewModel.getErrorState());
                calculatorViewModel.setLastSavedResult("Error");
            }
        } else {
            calculatorViewModel.setCurrentOperation("-");
        }
    }

    @Override
    public void pressDivide() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            try {
                BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
                BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                calculatorViewModel.setLastSavedResult(divisionResult.toString());
                calculatorViewModel.setFirstOperandString(divisionResult.toString());
                calculatorViewModel.setFirstNumber(divisionResult);
                calculatorViewModel.setSecondOperandString("");
                calculatorViewModel.setCurrentOperation("÷");
                calculatorViewModel.setDotUnpressed();
                calculatorViewModel.setState(calculatorViewModel.getDividePressedState());
            } catch (ArithmeticException e) {
                pressAllClear();
                calculatorViewModel.setState(calculatorViewModel.getErrorState());
                calculatorViewModel.setLastSavedResult("Error");
            }
        }
    }

    @Override
    public void pressRemain() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            try {
                BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
                BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                calculatorViewModel.setLastSavedResult(divisionResult.toString());
                calculatorViewModel.setFirstOperandString(divisionResult.toString());
                calculatorViewModel.setFirstNumber(divisionResult);
                calculatorViewModel.setSecondOperandString("");
                calculatorViewModel.setCurrentOperation("%");
                calculatorViewModel.setDotUnpressed();
                calculatorViewModel.setState(calculatorViewModel.getRemainPressedState());
            } catch (ArithmeticException e) {
                pressAllClear();
                calculatorViewModel.setState(calculatorViewModel.getErrorState());
                calculatorViewModel.setLastSavedResult("Error");
            }
        } else {
            calculatorViewModel.setCurrentOperation("%");
        }
    }

    @Override
    public void pressMultiply() {
        if (!calculatorViewModel.isSecondOperandStringEmpty()) {
            try {
                BigDecimal firstNumber = calculatorViewModel.getFirstNumber();
                BigDecimal secondNumber = calculatorViewModel.getSecondNumber();
                BigDecimal divisionResult = CalculatorModel.divide(firstNumber, secondNumber);

                calculatorViewModel.setLastSavedResult(divisionResult.toString());
                calculatorViewModel.setFirstOperandString(divisionResult.toString());
                calculatorViewModel.setFirstNumber(divisionResult);
                calculatorViewModel.setSecondOperandString("");
                calculatorViewModel.setCurrentOperation("×");
                calculatorViewModel.setDotUnpressed();
                calculatorViewModel.setState(calculatorViewModel.getMultiplyPressedState());
            } catch (ArithmeticException e) {
                pressAllClear();
                calculatorViewModel.setState(calculatorViewModel.getErrorState());
                calculatorViewModel.setLastSavedResult("Error");
            }
        } else {
            calculatorViewModel.setCurrentOperation("×");
        }
    }
}
