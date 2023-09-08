package org.travelrecord.Entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TripEntity {

    private Integer id;

    private String tripName;

    private Date startDate;
    private Date endDate;

    private String dirPath;



}