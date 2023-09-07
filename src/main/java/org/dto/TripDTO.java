package org.dto;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


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

<<<<<<< HEAD
        public static Response fromEntity(ResponseTripDTO responseTripDTO, List<ResponseItineraryDTO> itineraries) {
            return Response.builder()
                    .id(responseTripDTO.getId())
                    .tripName(responseTripDTO.getTripName())
                    .startDate(responseTripDTO.getStartDate())
                    .endDate(responseTripDTO.getEndDate())
//                    .itineraries()
=======
        public static Response fromEntity(Trip trip, List<Itinerary> itineraries) {
            List<ItineraryDTO.Response> itineraryDtoList  = itineraries.stream()
                    .map(ItineraryDTO.Response::fromEntity).toList();
            return Response.builder()
                    .id(trip.getId())
                    .tripName(trip.getTripName())
                    .startDate(trip.getStartDate())
                    .endDate(trip.getEndDate())
                    .itineraries(itineraryDtoList)
>>>>>>> bd239237ae78752cc08ae73995e651a33f0b47d5
                    .build();
        }
    }

}