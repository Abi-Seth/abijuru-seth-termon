package com.example.calculator.utils;

public class InvalidOperationException extends Exception {
    public InvalidOperationException(String message) {
        super(message);
    }
}
