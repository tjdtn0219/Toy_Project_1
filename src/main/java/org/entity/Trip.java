package org.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Trip {

    private Integer Id;

    private String tripName;

    private Date startDate;
    private Date endDate;

    private String itineraryFileName;

}