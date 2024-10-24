package com.koichi.assignment8.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 登録・更新・削除の各処理で、リクエストが正常に完了した際のレスポンスメッセージです。
 */
@Schema(description = "登録・更新・削除の各処理で、リクエストが正常に完了した際のレスポンスメッセージ")
public class StudentResponse {
    @Schema(description = "レスポンスメッセージ")
    private String message;

    public StudentResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
