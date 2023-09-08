package org.constant;

public class ViewMessage {
    public static final String SERVICE_NAME = "---------------------------------------------------------------\n" +
                                            "           # 여행 여정을 기록과 관리하는 SNS 서비스 #         \n" +
                                            "---------------------------------------------------------------\n";

    public static final String MAIN_MENU = "---------------------------------------------------------------\n" +
                                            "                          메뉴리스트                           \n" +
                                            "---------------------------------------------------------------\n" +
                                            "  여행기록(1), 여정기록(2), 여행조회(3), 여정조회(4), 종료(5)  \n" +
                                            "---------------------------------------------------------------\n" +
                                            "  시작하려면 메뉴 번호를 입력하세요: ";

    public static final String TRIP_SAVE_RESULT = "\n저장 완료!!\n" +
                                                "[여행기록정보 저장결과]\n" +
                                                "여행 ID: %s \n" +
                                                "여행 이름: %s\n" +
                                                "여행 출발날짜: %s\n" +
                                                "여행 도착날짜: %s\n";

    public static final String TRIP_SEARCH_RESULT = "\n[여행기록정보 조회결과]\n" +
                                                    "여행 ID: %s \n" +
                                                    "여행 이름: %s\n" +
                                                    "여행 출발날짜: %s\n" +
                                                    "여행 도착날짜: %s\n";

    public static final String ITINERARY_SAVE_RESULT = "\n저장 완료!!\n" +
                                                    "[여정기록정보 저장결과]\n" +
                                                    "여정 ID: %s \n" +
                                                    "여정 출발지: %s\n" +
                                                    "여정 도착지: %s\n" +
                                                    "여정 출발시간: %s\n" +
                                                    "여정 도착시간: %s\n" +
                                                    "여정 체크인시간: %s\n" +
                                                    "여정 체크아웃시간: %s\n";

    public static final String ITINERARY_SEARCH_RESULT = "\n[여정기록정보 조회결과]\n" +
                                                        "여정 ID: %s \n" +
                                                        "여정 출발지: %s\n" +
                                                        "여정 도착지: %s\n" +
                                                        "여정 출발시간: %s\n" +
                                                        "여정 도착시간: %s\n" +
                                                        "여정 체크인시간: %s\n" +
                                                        "여정 체크아웃시간: %s\n";
}
