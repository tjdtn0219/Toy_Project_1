package org.frontcontroller.controller;

import org.dto.ItineraryDTO;
import org.dto.respository.ItineraryRepository;
import org.entity.Itinerary;
import org.frontcontroller.Controller;
import org.view.ItineraryView;

import java.text.ParseException;

public class ItinerarySaveController implements Controller {

    private ItineraryRepository itineraryRepository = ItineraryRepository.getInstance();
    @Override
    public void process() throws ParseException {
//        System.out.println("여정 기록");

        ItineraryView itineraryView = new ItineraryView();
        ItineraryDTO.Request request = itineraryView.getDtoFromInput();

        Itinerary itinerary = Itinerary.builder()
                .tripId(request.getTripId())
                .departurePlace(request.getDeparturePlace())
                .destination(request.getDestination())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .checkInTime(request.getCheckInTime())
                .checkOutTime(request.getCheckOutTime())
                .build();

        Itinerary savedItinerary = itineraryRepository.save(itinerary); //이 부분을 Model에서 구현해주면 됩니다.

        System.out.println("**저장 내역**\n" + savedItinerary + "\n");
    }
}
