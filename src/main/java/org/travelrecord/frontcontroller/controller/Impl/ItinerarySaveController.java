package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.dto.requestItineraryDTO;
import org.travelrecord.Entity.ItineraryEntity;
import org.travelrecord.Entity.TripEntity;
import org.travelrecord.dto.requestTripDTO;
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

        List<TripEntity> responseJSONTripDTOS = tripModel.JSONfindAll();
        tripView.showDtoList(
                responseJSONTripDTOS.stream().map(requestTripDTO.Response::fromEntity).toList()
        );

        requestItineraryDTO.Request request = itineraryView.getDtoFromInput();

        ItineraryEntity itineraryEntity = itineraryEntity.builder()
                .tripId(request.getTripId())
                .departurePlace(request.getDeparturePlace())
                .destination(request.getDestination())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .checkInTime(request.getCheckInTime())
                .checkOutTime(request.getCheckOutTime())
                .build();

        ItineraryEntity savedItinerary
                = itineraryModel.save(request.getTripId(), itineraryEntity);

        itineraryView.showSaveResult(
                requestItineraryDTO.Response.fromEntity(savedItinerary)
        );
    }
}
