package org.frontcontroller.controller;

import org.dto.ItineraryDTO;
import org.dto.respository.ItineraryRepository;
import org.entity.Itinerary;
import org.frontcontroller.Controller;
import org.view.ConsoleView;
import org.view.ItineraryView;

import java.util.List;
import java.util.stream.Collectors;

public class ItineraryListController implements Controller {

    private ItineraryRepository itineraryRepository = ItineraryRepository.getInstance();

    @Override
    public void process() {
//        System.out.println("여정 조회");

        ItineraryView itineraryView = new ItineraryView();

        int tripId = itineraryView.getTripIdForItineraries();
        List<Itinerary> itineraries = itineraryRepository.findAllByTripId(tripId);  //이 부분을 Model에서 구현해주시면 됩니다.

        List<ItineraryDTO.Response> responseList = itineraries.stream()
                .map(ItineraryDTO.Response::fromEntity).toList();
        itineraryView.showDtoList(responseList);

    }
}
