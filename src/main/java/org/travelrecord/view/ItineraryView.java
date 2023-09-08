package org.travelrecord.view;

import org.travelrecord.constant.FileType;
import org.travelrecord.dto.requestItineraryDTO;
import org.travelrecord.exception.ViewErrorCode;
import org.travelrecord.exception.ViewException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.travelrecord.constant.ViewMessage.*;

public class ItineraryView implements ItemView<requestItineraryDTO.Request, requestItineraryDTO.Response> {

    @Override
    public requestItineraryDTO.Request getDtoFromInput() {
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
    public void showDtoList(List<requestItineraryDTO.Response> itineraryDtoList) {
        itineraryDtoList.forEach(this::showItineraryTable);
    }

    @Override
    public void showSaveResult(requestItineraryDTO.Response dto) {
        String result = String.format(
                ITINERARY_SAVE_RESULT,
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
    public FileType chooseFileType() {
        while (true) {
            try {
                System.out.print("\n파일 타입을 고르세요(JSON, CSV) : ");
                String fileType = sc.nextLine();
                fileType = fileType.toUpperCase();
                if(fileType.equals(FileType.JSON.toString())) {
                    return FileType.JSON;
                } else if (fileType.equals(FileType.CSV.toString())) {
                    return FileType.CSV;
                }
                throw new ViewException(ViewErrorCode.NOT_MATCHED_FILETYPE);
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

    private requestItineraryDTO.Request getRequest(int tripId) {
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
            System.out.print("여정 시작시간을 입력하세요 (YYYY-MM-DD hh:mm): ");
            departureTime = timeFormat.parse(sc.nextLine());
            System.out.print("여정 도착시간을 입력하세요 (YYYY-MM-DD hh:mm): ");
            arrivalTime = timeFormat.parse(sc.nextLine());
            System.out.print("여정 체크인 시간을 입력하세요 (YYYY-MM-DD hh:mm): ");
            checkInTime = timeFormat.parse(sc.nextLine());
            System.out.print("여정 체크아웃 시간을 입력하세요 (YYYY-MM-DD hh:mm): ");
            checkOutTime = timeFormat.parse(sc.nextLine());
        } catch (ParseException e) {
            throw new ViewException(ViewErrorCode.NOT_MATCHED_TIME_FORMAT);
        }

        return requestItineraryDTO.Request.builder()
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

    private void showItineraryTable(requestItineraryDTO.Response response) {
        String result = String.format(
                ITINERARY_SEARCH_RESULT,
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
