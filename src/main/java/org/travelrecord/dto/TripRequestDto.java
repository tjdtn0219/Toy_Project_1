package org.travelrecord.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TripRequestDto {
    private String tripName;
    private Date startDate;
    private Date endDate;

}