package org.travelrecord.exception;

import lombok.Getter;

@Getter
public class ViewException extends RuntimeException {
    private ViewErrorCode viewErrorCode;

    public ViewException(ViewErrorCode errorCode) {
        super(errorCode.getMessage());
        this.viewErrorCode = errorCode;
    }


}
