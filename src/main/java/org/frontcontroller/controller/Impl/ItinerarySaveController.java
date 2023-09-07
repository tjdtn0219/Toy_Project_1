package org.frontcontroller.controller.Impl;

import org.dto.ItineraryDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.ItineraryModelImpl;
import org.dto.ResponseItineraryDTO;
import org.view.ItineraryView;

import java.text.ParseException;

public class ItinerarySaveController implements Controller {

    private ItineraryModelImpl itineraryModel = new ItineraryModelImpl();
    @Override
    public void process() throws ParseException {
//        System.out.println("여정 기록");

        ItineraryView itineraryView = new ItineraryView();
        ItineraryDTO.Request request = itineraryView.getDtoFromInput();

        ResponseItineraryDTO responseItineraryDTO = ResponseItineraryDTO.builder()
                .tripId(request.getTripId())
                .departurePlace(request.getDeparturePlace())
                .destination(request.getDestination())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .checkInTime(request.getCheckInTime())
                .checkOutTime(request.getCheckOutTime())
                .build();

       ResponseItineraryDTO savedResponseItineraryDTO = itineraryModel.save(request.getTripId(),responseItineraryDTO);

        System.out.println("**저장 내역**\n" + savedResponseItineraryDTO + "\n");
    }
}
