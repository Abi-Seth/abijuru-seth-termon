package com.example.calculator.service;

import com.example.calculator.utils.InvalidOperationException;
import org.springframework.stereotype.Service;

@Service
public class MathService {
    public double doMath(double operand1, double operand2, String operation) throws InvalidOperationException {
        if ("/".equals(operation) && operand2 == (double) 0) {
            throw new InvalidOperationException("Cannot divide by 0");
        }

        return switch (operation) {
            case "*" -> operand1 * operand2;
            case "/" -> operand1 / operand2;
            case "+" -> operand1 + operand2;
            case "-" -> operand1 - operand2;
            case "**" -> Math.pow(operand1, operand2);
            case "log" -> operand1 * Math.log10(operand2);
            case "ln" -> operand1 * Math.log(operand2);
            default -> throw new RuntimeException("Unknown operation");
        };
    }
}
