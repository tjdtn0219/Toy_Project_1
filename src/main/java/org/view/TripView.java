package org.view;

import org.dto.TripDTO;
import org.exception.ViewErrorCode;
import org.exception.ViewException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class TripView implements ItemView<TripDTO.Request, TripDTO.Response> {

    @Override
    public TripDTO.Request getDtoFromInput() {
        System.out.println("기록할 여행 정보를 입력 하세요.\n");
        while (true) {
            try {
                TripDTO.Request request = getRequest();
                return request;
            } catch (ViewException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static TripDTO.Request getRequest() {
        String tripName;
        Date startDate;
        Date endDate;

        System.out.print("여행 이름을 입력 하세요: ");
        tripName = sc.nextLine();
        try {
            System.out.print("여행 시작 날짜를 입력 하세요 (YYYY-MM-DD): ");
            startDate = dateFormat.parse(sc.nextLine());
            System.out.print("여행 종료 날짜를 입력 하세요 (YYYY-MM-DD): ");
            endDate = dateFormat.parse(sc.nextLine());

            return TripDTO.Request.builder()
                    .tripName(tripName)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
        } catch (ParseException e) {
            throw new ViewException(ViewErrorCode.NOT_MATCHED_DATE_FORMAT);
        }
    }

    @Override
    public void showDtoList(List<TripDTO.Response> tripDtoList) {
        tripDtoList.forEach(this::showTripTable);
    }

    @Override
    public void showSaveResult(TripDTO.Response dto) {
        String result = String.format(
                "\n저장 완료!!\n" +
                        "[여행기록정보 저장결과]\n" +
                        "여행 ID: %s \n" +
                        "여행 이름: %s\n" +
                        "여행 출발날짜: %s\n" +
                        "여행 도착날짜: %s\n",
                dto.getId(),
                dto.getTripName(),
                dateFormat.format(dto.getStartDate()),
                dateFormat.format(dto.getEndDate())
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
