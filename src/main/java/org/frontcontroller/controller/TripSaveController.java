package org.frontcontroller.controller;

import org.dto.TripDTO;
import org.dto.respository.TripRepository;
import org.entity.Trip;
import org.frontcontroller.Controller;
import org.view.TripView;

import java.text.ParseException;

public class TripSaveController implements Controller {

    private TripRepository tripRepository = TripRepository.getInstance();

    @Override
    public void process() throws ParseException {
//        System.out.println("여행 기록");
        TripView tripView = new TripView();

        TripDTO.Request request = tripView.getDtoFromInput();

        Trip trip = Trip.builder()
                .tripName(request.getTripName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();//Dto -> Trip

        Trip savedTrip = tripRepository.save(trip);  //이것에 해당하는 부분을 Model에서 만들어주세요.

        System.out.println("**저장 내역**\n" + savedTrip + "\n");   //임시로 이렇게 설정함

    }
}
