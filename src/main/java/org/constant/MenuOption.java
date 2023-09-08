package org.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum MenuOption {
    TRIP_SAVE(1, "여행 기록"),
    ITINERARY_SAVE(2, "여정 기록"),
    TRIP_LIST(3, "여행 조회"),
    ITINERARY_LIST(4, "여정 조회"),
    TERMINATE(5, "종료")
    ;

    private final Integer optionKey;
    private final String description;

    private static final Map<Integer, String> map = Collections.unmodifiableMap(
                    Stream.of(values()).collect(Collectors.toMap(
                            MenuOption::getOptionKey, MenuOption::name
                    )));

    public static MenuOption of(final Integer optionKey) {
        return MenuOption.valueOf(map.get(optionKey));
    }
}
