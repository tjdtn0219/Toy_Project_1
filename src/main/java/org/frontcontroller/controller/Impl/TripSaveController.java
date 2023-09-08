package org.frontcontroller.controller.Impl;

import org.dto.ResponseTripDTO;
import org.dto.TripDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.TripModelImpl;
import org.model.TripModel;
import org.view.TripView;

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
