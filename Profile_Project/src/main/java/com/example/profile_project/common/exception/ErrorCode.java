package com.example.profile_project.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    PERSON_INFO_NOT_FOUND(HttpStatus.NOT_FOUND, "🟣 프로필 자체가 없음 🟣"),
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "🟣 해당 프로필에 이미지가 없음 🟣"),
    UPLOAD_FILE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "🔴 파일 업로드 실패 🔴")
    ;

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
