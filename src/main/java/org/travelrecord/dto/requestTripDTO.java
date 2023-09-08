package org.travelrecord.dto;

import lombok.*;
import org.travelrecord.Entity.TripEntity;

import java.util.Date;
import java.util.List;


public class requestTripDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request {
        private String tripName;
        private Date startDate;
        private Date endDate;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Response {
        private Integer id;
        private String tripName;
        private Date startDate;
        private Date endDate;
        private List<requestItineraryDTO.Response> itineraries;

        public static Response fromEntity(TripEntity tripEntity) {
            return Response.builder()
                    .id(tripEntity.getId())
                    .tripName(tripEntity.getTripName())
                    .startDate(tripEntity.getStartDate())
                    .endDate(tripEntity.getEndDate())
                    .build();
        }
    }

}