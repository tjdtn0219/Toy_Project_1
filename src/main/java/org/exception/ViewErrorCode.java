package org.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewErrorCode {
    NO_MENU_OPTION("\n[ERROR] 해당하는 메뉴 번호가 없습니다. 1-5사이의 숫자를 다시 입력해주세요.\n"),

    NOT_MATCHED_DATE_FORMAT("\n[ERROR] 날짜 포맷이 잘못 입력되었습니다. 날짜를 YYYY-MM-DD 형식에 맞춰 다시 입력해주세요.\n"),

    NOT_MATCHED_TIME_FORMAT("\n[ERROR] 날짜, 시간 포맷이 잘못 입력되었습니다. 날짜와 시간을 YYYY-MM-DD hh:mm 형식에 맞춰 다시 입력해주세요.\n"),

    NOT_MATCHED_INTEGER_FORMAT("\n[ERROR] ID는 숫자만 입력이 가능합니다. 다시 입력해주세요.\n"),

    NOT_MATCHED_FILETYPE("\n[ERROR] 파일 타입은 JSON과 CSV만 가능합니다. 다시 입력해주세요.\n"),
    ;

    private final String message;
}
