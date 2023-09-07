package org.dto;

import lombok.*;

import java.util.Date;

public class ItineraryDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {
        private Integer tripId;

        // 출발지, 도착지
        private String departurePlace;
        private String destination;

        // Date 타입으로 출,도착, 체크인,아웃 시간 저장
        // https://clsrn4561.tistory.com/15   <<  Date타입 참고 블로그
        private Date departureTime;
        private Date arrivalTime;
        private Date checkInTime;
        private Date checkOutTime;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Response {
        private Integer Id;
        private Integer tripId;

        private String departurePlace;
        private String destination;

        private Date departureTime;
        private Date arrivalTime;
        private Date checkInTime;
        private Date checkOutTime;
        public static Response fromEntity(ResponseItineraryDTO responseItineraryDTO) {
            return Response.builder()
                    .Id(responseItineraryDTO.getId())
                    .tripId(responseItineraryDTO.getTripId())
                    .departurePlace(responseItineraryDTO.getDeparturePlace())
                    .destination(responseItineraryDTO.getDestination())
                    .departureTime(responseItineraryDTO.getDepartureTime())
                    .arrivalTime(responseItineraryDTO.getArrivalTime())
                    .checkInTime(responseItineraryDTO.getCheckInTime())
                    .checkOutTime(responseItineraryDTO.getCheckOutTime())
                    .build();
        }
    }

}

