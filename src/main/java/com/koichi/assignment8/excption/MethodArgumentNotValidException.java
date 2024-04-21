package com.koichi.assignment8.excption;

/**
 * 登録処理の際に、name か birthPlace の
 * どちらかの項目が入力されてない時の例外処理になります。
 */
public class MethodArgumentNotValidException extends RuntimeException {
    public MethodArgumentNotValidException(String message) {
        super(message);
    }

}
