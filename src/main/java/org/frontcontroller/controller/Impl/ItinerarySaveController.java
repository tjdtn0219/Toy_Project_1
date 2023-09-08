package org.frontcontroller.controller.Impl;

import org.dto.ItineraryDTO;
import org.dto.ResponseItineraryDTO;
import org.dto.ResponseTripDTO;
import org.dto.TripDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.ItineraryModelImpl;
import org.model.Impl.TripModelImpl;
import org.model.TripModel;
import org.view.ItineraryView;
import org.view.TripView;

import java.text.ParseException;
import java.util.List;

public class ItinerarySaveController implements Controller {

    private ItineraryModelImpl itineraryModel = new ItineraryModelImpl();
    private TripModel tripModel = new TripModelImpl();
    private ItineraryView itineraryView = new ItineraryView();
    private TripView tripView = new TripView();

    @Override
    public void process() {

        List<ResponseTripDTO> responseJSONTripDTOS = tripModel.JSONfindAll();
        tripView.showDtoList(
                responseJSONTripDTOS.stream().map(TripDTO.Response::fromEntity).toList()
        );

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

        ResponseItineraryDTO savedItinerary
                = itineraryModel.save(request.getTripId(),responseItineraryDTO);

        itineraryView.showSaveResult(
                ItineraryDTO.Response.fromEntity(savedItinerary)
        );
    }
}
