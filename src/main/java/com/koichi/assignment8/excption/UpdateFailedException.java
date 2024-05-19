package com.koichi.assignment8.excption;

/**
 * updateGradeで進級する学年が指定されなかった時の
 * 例外処理になります。
 */
public class UpdateFailedException extends RuntimeException {
    public UpdateFailedException(String message) {
        super(message);
    }
}
