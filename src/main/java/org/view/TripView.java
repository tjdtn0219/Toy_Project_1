package org.view;

import org.dto.TripDTO;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TripView implements ItemView<TripDTO.Request, TripDTO.Response> {

    @Override
    public TripDTO.Request getDtoFromInput() throws ParseException {
        String tripName;
        Date startDate;
        Date endDate;

        System.out.println("기록할 여행 정보를 입력 하세요.");
        System.out.print("여행 이름을 입력 하세요: ");
        tripName = sc.nextLine();
        System.out.print("여행 시작 날짜를 입력 하세요: "); //을->를 수정
        startDate = dateFormat.parse(sc.nextLine());
        System.out.print("여행 종료 날짜를 입력 하세요: "); //을->를 수정
        endDate = dateFormat.parse(sc.nextLine());

        return TripDTO.Request.builder()
                .tripName(tripName)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }

    @Override
    public void showDtoList(List<TripDTO.Response> tripDtoList) {
        tripDtoList.forEach(this::showTripTable);
    }

    private void showTripTable(TripDTO.Response trip) {
        String result = String.format(
                "\n[여행기록정보 조회결과]\n" +
                        "여행 ID: %s \n" +
                        "여행 이름: %s\n" +
                        "여행 출발날짜: %s\n" +
                        "여행 도착날짜: %s\n",
                trip.getId(),
                trip.getTripName(),
                dateFormat.format(trip.getStartDate()),
                dateFormat.format(trip.getEndDate())
        );
        System.out.println(result);
    }
}
