package org.exception;

import lombok.Getter;

@Getter
public class ControllerException extends RuntimeException {
    private final ControllerErrorCode errorCode;

    public ControllerException(ControllerErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
