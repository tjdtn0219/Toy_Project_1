package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.Entity.TripEntity;
import org.travelrecord.dto.TripRequestDto;
import org.travelrecord.dto.TripResponseDto;
import org.travelrecord.frontcontroller.controller.Controller;
import org.travelrecord.model.Impl.TripModelImpl;
import org.travelrecord.model.TripModel;
import org.travelrecord.view.TripView;

public class TripSaveController implements Controller {

    private TripModel tripModel = new TripModelImpl();
    private TripView tripView = new TripView();

    @Override
    public void process() {

        TripRequestDto tripRequestDto = tripView.getDtoFromInput();

        TripEntity tripEntity = TripEntity.builder()
                .tripName(tripRequestDto.getTripName())
                .startDate(tripRequestDto.getStartDate())
                .endDate(tripRequestDto.getEndDate())
                .build();

        TripEntity savedTrip = tripModel.save(tripEntity);

        tripView.showSaveResult(TripResponseDto.fromEntity(savedTrip));
    }
}
