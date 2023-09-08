package org.travelrecord.view;

import org.travelrecord.constant.FileType;
import org.travelrecord.dto.TripRequestDto;
import org.travelrecord.dto.TripResponseDto;
import org.travelrecord.exception.ViewErrorCode;
import org.travelrecord.exception.ViewException;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.travelrecord.constant.ViewMessage.TRIP_SAVE_RESULT;
import static org.travelrecord.constant.ViewMessage.TRIP_SEARCH_RESULT;

public class TripView implements ItemView<TripRequestDto, TripResponseDto> {

    @Override
    public TripRequestDto getDtoFromInput() {
        System.out.println("기록할 여행 정보를 입력 하세요.\n");
        while (true) {
            try {
                TripRequestDto tripRequestDto = getRequest();
                return tripRequestDto;
            } catch (ViewException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static TripRequestDto getRequest() {
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

            return TripRequestDto.builder()
                    .tripName(tripName)
                    .startDate(startDate)
                    .endDate(endDate)
                    .build();
        } catch (ParseException e) {
            throw new ViewException(ViewErrorCode.NOT_MATCHED_DATE_FORMAT);
        }
    }

    @Override
    public void showDtoList(List<TripResponseDto> tripDtoList) {
        tripDtoList.forEach(this::showTripTable);
    }

    @Override
    public void showSaveResult(TripResponseDto dto) {
        String result = String.format(
                TRIP_SAVE_RESULT,
                dto.getTripName(),
                dateFormat.format(dto.getStartDate()),
                dateFormat.format(dto.getEndDate())
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

    private void showTripTable(TripResponseDto trip) {
        String result = String.format(
                TRIP_SEARCH_RESULT,
                trip.getId(),
                trip.getTripName(),
                dateFormat.format(trip.getStartDate()),
                dateFormat.format(trip.getEndDate())
        );
        System.out.println(result);
    }
}
