package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.Entity.TripEntity;
import org.travelrecord.dto.requestTripDTO;
import org.travelrecord.frontcontroller.controller.Controller;
import org.travelrecord.model.Impl.TripModelImpl;
import org.travelrecord.model.TripModel;
import org.travelrecord.view.TripView;

public class TripSaveController implements Controller {

    private TripModel tripModel = new TripModelImpl();
    private TripView tripView = new TripView();

    @Override
    public void process() {

        requestTripDTO.Request request = tripView.getDtoFromInput();

        TripEntity tripEntity = tripEntity.builder()
                .tripName(request.getTripName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        TripEntity savedTrip = tripModel.save(tripEntity);

        tripView.showSaveResult(requestTripDTO.Response.fromEntity(savedTrip));
    }
}
