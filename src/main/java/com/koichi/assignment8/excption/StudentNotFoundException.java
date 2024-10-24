package com.koichi.assignment8.excption;

/**
 * idを指定するCLOD処理で学生が見つからない場合の例外処理になります。
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
