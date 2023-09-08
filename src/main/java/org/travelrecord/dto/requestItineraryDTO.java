package org.travelrecord.dto;

import lombok.*;
import org.travelrecord.Entity.ItineraryEntity;

import java.util.Date;

public class requestItineraryDTO {

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
        public static Response fromEntity(ItineraryEntity itineraryEntity) {
            return Response.builder()
                    .Id(itineraryEntity.getId())
                    .tripId(itineraryEntity.getTripId())
                    .departurePlace(itineraryEntity.getDeparturePlace())
                    .destination(itineraryEntity.getDestination())
                    .departureTime(itineraryEntity.getDepartureTime())
                    .arrivalTime(itineraryEntity.getArrivalTime())
                    .checkInTime(itineraryEntity.getCheckInTime())
                    .checkOutTime(itineraryEntity.getCheckOutTime())
                    .build();
        }
    }

}

