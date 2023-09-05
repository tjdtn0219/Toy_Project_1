package org.dto;

import lombok.*;
import org.entity.Itinerary;
import org.entity.Trip;

import java.util.Date;
import java.util.List;


public class TripDTO {

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
        private List<ItineraryDTO.Response> itineraries;

        public static Response fromEntity(Trip trip, List<Itinerary> itineraries) {
            return Response.builder()
                    .id(trip.getId())
                    .tripName(trip.getTripName())
                    .startDate(trip.getStartDate())
                    .endDate(trip.getEndDate())
//                    .itineraries()
                    .build();
        }
    }

}