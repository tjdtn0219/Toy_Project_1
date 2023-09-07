package org.dto;

import lombok.*;

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

        public static Response fromEntity(ResponseTripDTO responseTripDTO, List<ResponseItineraryDTO> itineraries) {
            return Response.builder()
                    .id(responseTripDTO.getId())
                    .tripName(responseTripDTO.getTripName())
                    .startDate(responseTripDTO.getStartDate())
                    .endDate(responseTripDTO.getEndDate())
//                    .itineraries()
                    .build();
        }
    }

}