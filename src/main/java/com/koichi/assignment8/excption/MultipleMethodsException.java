package com.koichi.assignment8.excption;

/**
 * GETリクエストのクエリパラメータ検索でカラムを2つ以上を選んだ時の
 * 例外処理になります。
 */

public class MultipleMethodsException extends RuntimeException {

    public MultipleMethodsException(String message) {
        super(message);
    }

}
