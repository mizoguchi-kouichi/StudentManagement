package com.koichi.assignment8;

/**
 * クライアント様が、登録処理の際に、name かbirthPlaceの
 * どれかの項目が入力されてない時の例外処理になります。
 */

public class AnyItemIsNullException extends RuntimeException {
    public AnyItemIsNullException(String message) {
        super(message);
    }

}
