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
public class ItinerariesDTO {

    // id
    private int itineraryId;

    // 출발지, 도착지
    private String departurePlace;
    private String destination;

    // Date 타입으로 출,도착, 체크인,아웃 시간 저장
    // https://clsrn4561.tistory.com/15   <<  Date타입 참고 블로그
    private Date departureTime;
    private Date arrivalTime;
    private Date checkInTime;
    private Date checkOutTime;


}

