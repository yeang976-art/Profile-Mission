package com.example.profile_project.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final ErrorCode code;

    public ServiceException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}
