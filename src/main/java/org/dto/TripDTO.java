package org.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    // id
    private int tripId;

    // 여행 이름
    private String tripName;

    // Date 타입으로 시간 저장
    // https://clsrn4561.tistory.com/15   <<  Date타입 참고 블로그
    private Date startDate;
    private Date endDate;

    // 여정(Itineraries) 객체 배열
    private Itineraries[] itineraries;

}