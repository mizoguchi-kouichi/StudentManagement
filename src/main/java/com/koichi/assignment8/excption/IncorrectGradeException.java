package com.koichi.assignment8.excption;

public class IncorrectGradeException extends RuntimeException {
    public IncorrectGradeException(String message) {
        super(message);
    }
}
