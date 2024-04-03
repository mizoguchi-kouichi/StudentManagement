package com.koichi.assignment8.excption;

/**
 * 登録処理の際に、name か birthPlace の
 * どちらかの項目が入力されてない時の例外処理になります。
 */

public class AnyItemIsNullException extends RuntimeException {
    public AnyItemIsNullException(String message) {
        super(message);
    }

}
