package com.koichi.assignment8.excption;

/**
 * idを指定するCRUD処理やクエリパラメータ検索するREAD処理の際に、指定する数値が数字以外でリクエストされた時の例外処理です。
 */
public class MethodArgumentTypeMismatchException extends RuntimeException {

    public MethodArgumentTypeMismatchException(String message) {
        super(message);
    }
}
