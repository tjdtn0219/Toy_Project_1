package org.frontcontroller.controller;

import org.dto.TripDTO;
import org.dto.respository.ItineraryRepository;
import org.dto.respository.TripRepository;
import org.entity.Itinerary;
import org.entity.Trip;
import org.frontcontroller.Controller;
import org.view.TripView;

import java.util.ArrayList;
import java.util.List;

public class TripListController implements Controller {

    private TripRepository tripRepository = TripRepository.getInstance();
    private ItineraryRepository itineraryRepository = ItineraryRepository.getInstance();

    @Override
    public void process() {
//        System.out.println("여행 조회");
        TripView tripView = new TripView();

        List<Trip> trips = tripRepository.findAll();    //이 부분을 Model에서 만들어주세요.

        List<TripDTO.Response> responseList = new ArrayList<>();
        for (Trip trip : trips) {
            List<Itinerary> itineraries = itineraryRepository.findAllByTripId(trip.getId());    //이 부분을 Model에서 구현

            TripDTO.Response response = TripDTO.Response.fromEntity(trip, itineraries);
            responseList.add(response);
        }

        tripView.showDtoList(responseList);

    }
}
