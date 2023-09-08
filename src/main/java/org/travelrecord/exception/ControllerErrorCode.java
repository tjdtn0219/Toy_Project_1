package org.travelrecord.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControllerErrorCode {

    CONTROLLER_NOT_FOUND("컨트롤러를 찾을 수가 없습니다.");

    private final String message;
}
