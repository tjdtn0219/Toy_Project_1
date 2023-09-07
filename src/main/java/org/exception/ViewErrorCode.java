package org.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewErrorCode {
    NO_MENU_OPTION("해당하는 메뉴 번호가 없습니다. 1-5사이로 다시 입력 하세요."),

    NOT_MATCHED_DATE_FORMAT("날짜 포맷이 잘못 입력되었습니다. yyyy-mm-dd로 입력 하세요")
    ;

    private final String message;
}
