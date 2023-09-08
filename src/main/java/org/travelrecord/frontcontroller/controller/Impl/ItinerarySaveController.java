package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.Entity.ItineraryEntity;
import org.travelrecord.Entity.TripEntity;
import org.travelrecord.dto.ItineraryRequestDto;
import org.travelrecord.dto.ItineraryResponseDto;
import org.travelrecord.dto.TripResponseDto;
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
                responseJSONTripDTOS.stream().map(TripResponseDto::fromEntity).toList()
        );

        ItineraryRequestDto itineraryRequestDto = itineraryView.getDtoFromInput();

        ItineraryEntity itineraryEntity = ItineraryEntity.builder()
                .tripId(itineraryRequestDto.getTripId())
                .departurePlace(itineraryRequestDto.getDeparturePlace())
                .destination(itineraryRequestDto.getDestination())
                .departureTime(itineraryRequestDto.getDepartureTime())
                .arrivalTime(itineraryRequestDto.getArrivalTime())
                .checkInTime(itineraryRequestDto.getCheckInTime())
                .checkOutTime(itineraryRequestDto.getCheckOutTime())
                .build();

        ItineraryEntity savedItinerary
                = itineraryModel.save(itineraryRequestDto.getTripId(), itineraryEntity);

        itineraryView.showSaveResult(
                ItineraryResponseDto.fromEntity(savedItinerary)
        );
    }
}
