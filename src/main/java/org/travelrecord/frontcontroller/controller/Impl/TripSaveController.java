package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.dto.ResponseTripDTO;
import org.travelrecord.dto.TripDTO;
import org.travelrecord.frontcontroller.controller.Controller;
import org.travelrecord.model.Impl.TripModelImpl;
import org.travelrecord.model.TripModel;
import org.travelrecord.view.TripView;

public class TripSaveController implements Controller {

    private TripModel tripModel = new TripModelImpl();
    private TripView tripView = new TripView();

    @Override
    public void process() {

        TripDTO.Request request = tripView.getDtoFromInput();

        ResponseTripDTO responseTripDTO = ResponseTripDTO.builder()
                .tripName(request.getTripName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        ResponseTripDTO savedTrip = tripModel.save(responseTripDTO);

        tripView.showSaveResult(TripDTO.Response.fromEntity(savedTrip));
    }
}
