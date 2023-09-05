package org.view;

import org.dto.ItineraryDTO;
import org.dto.TripDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ItineraryView implements ItemView<ItineraryDTO.Request, ItineraryDTO.Response> {

    @Override
    public ItineraryDTO.Request getDtoFromInput() throws ParseException {
        int tripId;
        String departurePlace;
        String destination;

        Date departureTime;
        Date arrivalTime;
        Date checkInTime;
        Date checkOutTime;

        System.out.println("기록할 여정 정보를 입력 하세요.");

        System.out.print("여정을 저장할 여행 Id를 입력 하세요: ");
        tripId = Integer.parseInt(sc.nextLine());
        System.out.print("여정 출발지를 입력하세요: ");
        departurePlace = sc.nextLine();
        System.out.print("여정 도착지를 입력하세요: ");
        destination = sc.nextLine();
        System.out.print("여정 시작시간을 입력하세요: ");
        departureTime = timeFormat.parse(sc.nextLine());
        System.out.print("여정 도착시간을 입력하세요: ");
        arrivalTime = timeFormat.parse(sc.nextLine());
        System.out.print("여정 체크인 시간을 입력하세요: ");
        checkInTime = timeFormat.parse(sc.nextLine());
        System.out.print("여정 체크아웃 시간을 입력하세요: ");
        checkOutTime = timeFormat.parse(sc.nextLine());


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

    @Override
    public void showDtoList(List<ItineraryDTO.Response> itineraryDtoList) {
        itineraryDtoList.forEach(this::showItineraryTable);
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


    // 찾고 싶은 여정이 속해 있는 여행의 Id를 이용해서 여행을 조회
    public int getTripIdForItineraries() {
        System.out.print("찾고 싶은 여정의 여행 Id를 입력하세요: ");
        return Integer.parseInt(sc.nextLine());
    }
}
