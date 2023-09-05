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

    // id
    private int Id;

    // 여행 이름
    private String tripName;

    // Date 타입으로 시간 저장
    // https://clsrn4561.tistory.com/15   <<  Date타입 참고 블로그
    private Date startDate;
    private Date endDate;

    // 여정(Itineraries) 객체 배열
    private List<Itinerary> itineraries;

}