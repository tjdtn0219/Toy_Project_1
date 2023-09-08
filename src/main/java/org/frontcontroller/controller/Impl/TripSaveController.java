package org.frontcontroller.controller.Impl;

import org.dto.ItineraryDTO;
import org.dto.TripDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.TripModelImpl;
import org.dto.ResponseTripDTO;
import org.model.TripModel;
import org.view.ItineraryView;
import org.view.TripView;

import java.text.ParseException;

public class TripSaveController implements Controller {

    private TripModel tripModel = new TripModelImpl();
    private TripView tripView = new TripView();

    @Override
    public void process() throws ParseException {
//        System.out.println("여행 기록");

        TripDTO.Request request = tripView.getDtoFromInput();

        ResponseTripDTO responseTripDTO = ResponseTripDTO.builder()
                .tripName(request.getTripName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();//Dto -> Trip

        ResponseTripDTO savedResponseTripDTO = tripModel.save(responseTripDTO);

        tripView.showSaveResult(TripDTO.Response.fromEntity(savedResponseTripDTO));
    }
}
