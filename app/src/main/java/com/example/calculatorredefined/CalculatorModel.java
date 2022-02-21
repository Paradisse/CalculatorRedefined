package com.example.calculatorredefined;

import java.math.BigDecimal;

public class CalculatorModel {

    public static BigDecimal add(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }

    public static BigDecimal subtract(BigDecimal first, BigDecimal second) {
        return first.subtract(second);
    }

    public static BigDecimal divide(BigDecimal first, BigDecimal second) throws ArithmeticException {
        return first.divide(second, 2, BigDecimal.ROUND_HALF_EVEN);
    }

    public static BigDecimal multiply(BigDecimal first, BigDecimal second) {
        return first.multiply(second);
    }

    public static BigDecimal remainder(BigDecimal first, BigDecimal second) {
        return first.remainder(second);
    }

    public static BigDecimal negate(BigDecimal first) {
        first = first.negate();
        return first;
    }
}
