package org.travelrecord.dto;

import lombok.*;
import org.travelrecord.Entity.ItineraryEntity;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ItineraryResponseDto {
    private Integer Id;
    private Integer tripId;

    private String departurePlace;
    private String destination;

    private Date departureTime;
    private Date arrivalTime;
    private Date checkInTime;
    private Date checkOutTime;
    public static ItineraryResponseDto fromEntity(ItineraryEntity itineraryEntity) {
        return ItineraryResponseDto.builder()
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