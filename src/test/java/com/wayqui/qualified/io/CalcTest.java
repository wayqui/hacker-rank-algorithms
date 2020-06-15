package com.wayqui.qualified.io;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcTest {

    private static final String EXPR_SEPARATOR = " ";
    private static final String PLUS_SIGN = "+";
    private static final String MINUS_SIGN = "-";
    private static final String TIMES_SIGN = "*";
    private static final String DIVISION_SIGN = "/";

    private static List<String> OPERATIONS =
            Arrays.asList(PLUS_SIGN, MINUS_SIGN, TIMES_SIGN, DIVISION_SIGN);

    @Test
    public void test() {
        assertThat(evaluate("1 2 3.5")).isEqualTo(3.5d);
        assertThat(evaluate("10000 123 +")).isEqualTo(10123);
        assertThat(evaluate("20 4 3 + 2 * -")).isEqualTo(6);
        assertThat(evaluate("5 1 2 + 4 * + 3 -")).isEqualTo(14);
        assertThat(evaluate("0")).isEqualTo(0);
    }

    public double evaluate(String expr) {
        if (expr == null || expr.trim().isEmpty()) return 0;

        Stack<String> elementStack = new Stack<>();

        String[] items = expr.split(EXPR_SEPARATOR);

        Arrays.stream(items).forEach(element -> {
            if (OPERATIONS.contains(element)) {
                String first = elementStack.pop();
                String second = elementStack.pop();
                Double result = executeOperation(second, first, element);
                elementStack.push(String.valueOf(result));
            } else {
                elementStack.push(element);
            }
        });

        return Double.parseDouble(elementStack.pop());
    }

    private Double executeOperation(String firstElement, String secondElement, String element) {
        double firstNumber = Double.parseDouble(firstElement);
        double secondNumber = Double.parseDouble(secondElement);

        if (element.equals(PLUS_SIGN))
            return ArithmeticOperation.plus.getResult(firstNumber, secondNumber);
        if (element.equals(MINUS_SIGN))
            return ArithmeticOperation.minus.getResult(firstNumber, secondNumber);
        if (element.equals(TIMES_SIGN))
            return ArithmeticOperation.times.getResult(firstNumber, secondNumber);
        if (element.equals(DIVISION_SIGN))
            return ArithmeticOperation.division.getResult(firstNumber, secondNumber);

        return 0D;
    }

    enum ArithmeticOperation {

        plus(PLUS_SIGN, Double::sum),
        minus(MINUS_SIGN, (a, b) -> a - b),
        times(TIMES_SIGN, (a, b) -> a * b),
        division(DIVISION_SIGN, (a, b) -> a / b);

        private final String symbol;
        private final DoubleBinaryOperator operator;

        ArithmeticOperation(String symbol, DoubleBinaryOperator operator) {
            this.symbol = symbol;
            this.operator = operator;
        }

        public double getResult(double d1, double d2) {
            return operator.applyAsDouble(d1, d2);
        }

    }
}
