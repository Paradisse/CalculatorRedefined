package com.example.calculatorredefined;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorModel implements Serializable {

    public BigDecimal add(BigDecimal first, BigDecimal second) { return first.add(second); }
    public BigDecimal subtract(BigDecimal first, BigDecimal second) { return first.subtract(second); }
    public BigDecimal divide(BigDecimal first, BigDecimal second) throws ArithmeticException { return first.divide(second, 2, BigDecimal.ROUND_HALF_EVEN); }
    public BigDecimal multiply(BigDecimal first, BigDecimal second) { return first.multiply(second); }
    public BigDecimal remainder(BigDecimal first, BigDecimal second) { return first.remainder(second); }
    public BigDecimal negate(BigDecimal first) { first = first.negate(); return first; }
}
