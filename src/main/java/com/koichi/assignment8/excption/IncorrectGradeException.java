package com.koichi.assignment8.excption;

/**
 * updateGradeで進級する学年が指定されなかった時の
 * 例外処理になります。
 */

public class IncorrectGradeException extends RuntimeException {
    public IncorrectGradeException(String message) {
        super(message);
    }
}
