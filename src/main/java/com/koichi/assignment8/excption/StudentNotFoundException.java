package com.koichi.assignment8.excption;

/**
 * GETリクエストで指定したidのstudentがいなかった際の
 * 例外処理になります。
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }

}
