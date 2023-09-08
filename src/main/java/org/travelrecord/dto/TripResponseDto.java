package org.travelrecord.dto;

import lombok.*;
import org.travelrecord.Entity.TripEntity;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TripResponseDto {
    private Integer id;
    private String tripName;
    private Date startDate;
    private Date endDate;

    public static TripResponseDto fromEntity(TripEntity tripEntity) {
        return TripResponseDto.builder()
                .id(tripEntity.getId())
                .tripName(tripEntity.getTripName())
                .startDate(tripEntity.getStartDate())
                .endDate(tripEntity.getEndDate())
                .build();
    }
}