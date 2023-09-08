package org.travelrecord.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ItineraryRequestDto {
    private Integer tripId;

    // 출발지, 도착지
    private String departurePlace;
    private String destination;

    private Date departureTime;
    private Date arrivalTime;
    private Date checkInTime;
    private Date checkOutTime;

}
