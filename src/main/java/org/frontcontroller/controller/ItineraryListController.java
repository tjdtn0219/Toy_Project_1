package org.frontcontroller.controller;

import org.frontcontroller.Controller;
import org.view.ConsoleView;

public class ItineraryListController implements Controller {

    @Override
    public void process(ConsoleView consoleView) {
        int tripId = consoleView.receiveFindTripId();   //어느 여행의 여정들을 찾을건지

//        List<Itinerary> itineraries = Model.findAllItinerariesByTripId(tripId);
//        consoleView.showItinerariesInformation(trips);
    }
}
