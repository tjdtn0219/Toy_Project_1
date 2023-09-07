package org.frontcontroller.controller.Impl;

import org.dto.TripDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.TripModelImpl;
import org.dto.ResponseTripDTO;
import org.model.TripModel;
import org.view.TripView;

import java.text.ParseException;

public class TripSaveController implements Controller {

    private TripModel tripModel = new TripModelImpl();

    @Override
    public void process() throws ParseException {
//        System.out.println("여행 기록");
        TripView tripView = new TripView();

        TripDTO.Request request = tripView.getDtoFromInput();

        ResponseTripDTO responseTripDTO = ResponseTripDTO.builder()
                .tripName(request.getTripName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();//Dto -> Trip

        ResponseTripDTO savedResponseTripDTO = tripModel.save(responseTripDTO);


        System.out.println("**저장 내역**\n" + savedResponseTripDTO + "\n");

       // System.out.println("**저장 내역**\n" + savedTrip + "\n");   //임시로 이렇게 설정함


    }
}
