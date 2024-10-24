package com.koichi.assignment8.excption;

/**
 * READ処理のクエリパラメータ検索で、2つ以上のカラムを同時に指定した場合の例外処理です。
 */
public class MultipleMethodsException extends RuntimeException {

    public MultipleMethodsException(String message) {
        super(message);
    }
}
