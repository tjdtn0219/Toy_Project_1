package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.dto.ItineraryDTO;
import org.travelrecord.dto.ResponseItineraryDTO;
import org.travelrecord.dto.ResponseTripDTO;
import org.travelrecord.dto.TripDTO;
import org.travelrecord.frontcontroller.controller.Controller;
import org.travelrecord.model.Impl.ItineraryModelImpl;
import org.travelrecord.model.Impl.TripModelImpl;
import org.travelrecord.model.TripModel;
import org.travelrecord.view.ItineraryView;
import org.travelrecord.view.TripView;

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
