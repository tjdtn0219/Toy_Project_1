package org.frontcontroller.controller;

import org.dto.ItineraryDTO;
import org.frontcontroller.Controller;
import org.view.ConsoleView;

import java.text.ParseException;

public class ItinerarySaveController implements Controller {

    @Override
    public void process(ConsoleView consoleView) throws ParseException {
        ItineraryDTO.Request itineraryDTO = consoleView.writeItinerariesInformation();
        System.out.println(itineraryDTO.toString());
        //Model.findTripById(tripId) 호출 : 만약 없을 시 예외처리 예정
        //Model.saveItinerary(itineraryDTO) 호출
    }
}
