package org.view;

import org.dto.ItineraryDTO;
import org.exception.ViewErrorCode;
import org.exception.ViewException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ItineraryView implements ItemView<ItineraryDTO.Request, ItineraryDTO.Response> {

    @Override
    public ItineraryDTO.Request getDtoFromInput() {
        System.out.println("기록할 여정 정보를 입력 하세요.\n");
        while (true) {
            try {
                int tripId = getTripId();
                return getRequest(tripId);
            } catch (ViewException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void showDtoList(List<ItineraryDTO.Response> itineraryDtoList) {
        itineraryDtoList.forEach(this::showItineraryTable);
    }

    @Override
    public void showSaveResult(ItineraryDTO.Response dto) {
        String result = String.format(
                "\n저장 완료!!\n" +
                        "[여정기록정보 저장결과]\n" +
                        "여정 ID: %s \n" +
                        "여정 출발지: %s\n" +
                        "여정 도착지: %s\n" +
                        "여정 출발시간: %s\n" +
                        "여정 도착시간: %s\n" +
                        "여정 체크인시간: %s\n" +
                        "여정 체크아웃시간: %s\n",
                dto.getId(),
                dto.getDeparturePlace(),
                dto.getDestination(),
                timeFormat.format(dto.getDepartureTime()),
                timeFormat.format(dto.getArrivalTime()),
                timeFormat.format(dto.getCheckInTime()),
                timeFormat.format(dto.getCheckOutTime())
        );

        System.out.println(result);
    }

    @Override
    public String chooseFileType() {
        while (true) {
            try {
                System.out.print("파일 타입을 고르세요(JSON, CSV) : ");
                String fileType = sc.nextLine();
                if (!fileType.equals("JSON") && !fileType.equals("CSV")) {
                    throw new ViewException(ViewErrorCode.NOT_MATCHED_FILETYPE);
                }
                return fileType;
            } catch (ViewException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // 찾고 싶은 여정이 속해 있는 여행의 Id를 이용해서 여행을 조회
    public int getTripIdForItineraries() {
        System.out.print("찾고 싶은 여정의 여행 ID를 입력하세요: ");
        return Integer.parseInt(sc.nextLine());
    }

    private ItineraryDTO.Request getRequest(int tripId) {
        String departurePlace;
        String destination;
        Date departureTime;
        Date arrivalTime;
        Date checkInTime;
        Date checkOutTime;

        System.out.print("여정 출발지를 입력하세요: ");
        departurePlace = sc.nextLine();
        System.out.print("여정 도착지를 입력하세요: ");
        destination = sc.nextLine();
        try {
            System.out.print("여정 시작시간을 입력하세요: ");
            departureTime = timeFormat.parse(sc.nextLine());
            System.out.print("여정 도착시간을 입력하세요: ");
            arrivalTime = timeFormat.parse(sc.nextLine());
            System.out.print("여정 체크인 시간을 입력하세요: ");
            checkInTime = timeFormat.parse(sc.nextLine());
            System.out.print("여정 체크아웃 시간을 입력하세요: ");
            checkOutTime = timeFormat.parse(sc.nextLine());
        } catch (ParseException e) {
            throw new ViewException(ViewErrorCode.NOT_MATCHED_TIME_FORMAT);
        }

        return ItineraryDTO.Request.builder()
                .tripId(tripId)
                .departurePlace(departurePlace)
                .destination(destination)
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .checkInTime(checkInTime)
                .checkOutTime(checkOutTime)
                .build();
    }

    private static int getTripId() {
        int tripId;
        System.out.print("여정을 저장할 여행 Id를 입력 하세요: ");
        try {
            tripId = Integer.parseInt(sc.nextLine());
            return tripId;
        } catch (NumberFormatException e) {
            throw new ViewException(ViewErrorCode.NOT_MATCHED_INTEGER_FORMAT);
        }
    }

    private void showItineraryTable(ItineraryDTO.Response response) {
        String result = String.format(
                "\n[여정기록정보 조회결과]\n" +
                        "여정 ID: %s \n" +
                        "여정 출발지: %s\n" +
                        "여정 도착지: %s\n" +
                        "여정 출발시간: %s\n" +
                        "여정 도착시간: %s\n" +
                        "여정 체크인시간: %s\n" +
                        "여정 체크아웃시간: %s\n",
                response.getId(),
                response.getDeparturePlace(),
                response.getDestination(),
                timeFormat.format(response.getDepartureTime()),
                timeFormat.format(response.getArrivalTime()),
                timeFormat.format(response.getCheckInTime()),
                timeFormat.format(response.getCheckOutTime())
        );

        System.out.println(result);
    }
}
