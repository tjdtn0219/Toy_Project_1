package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.Entity.ItineraryEntity;
import org.travelrecord.Entity.TripEntity;
import org.travelrecord.constant.FileType;
import org.travelrecord.dto.ItineraryResponseDto;
import org.travelrecord.dto.TripResponseDto;
import org.travelrecord.frontcontroller.controller.Controller;
import org.travelrecord.model.Impl.ItineraryModelImpl;
import org.travelrecord.model.Impl.TripModelImpl;
import org.travelrecord.model.ItineraryModel;
import org.travelrecord.model.TripModel;
import org.travelrecord.view.ItineraryView;
import org.travelrecord.view.TripView;

import java.util.List;
import java.util.stream.Collectors;

import static org.travelrecord.constant.FileType.CSV;
import static org.travelrecord.constant.FileType.JSON;

public class ItineraryListController implements Controller {

    private ItineraryModel itineraryModel = new ItineraryModelImpl();
    private TripModel tripModel = new TripModelImpl();
    private ItineraryView itineraryView = new ItineraryView();
    private TripView tripView = new TripView();

    @Override
    public void process() {

        List<TripEntity> responseJSONTripDTOS = tripModel.JSONfindAll();
        tripView.showDtoList(
                responseJSONTripDTOS.stream().map(TripResponseDto::fromEntity).toList()
        );

        int tripId = itineraryView.getTripIdForItineraries();

        FileType type = itineraryView.chooseFileType();
        List<ItineraryEntity> itineraryList = null;

        if(type.equals(JSON)) {
            itineraryList = itineraryModel.findAllitineraryJsonByTripId(tripId);
        } else if(type.equals(CSV)) {
            itineraryList = itineraryModel.findAllitineraryCsvByTripId(tripId);
        }

        List<ItineraryResponseDto> itineraryResponseDtoList = itineraryList.stream()
                .map(ItineraryResponseDto::fromEntity).collect(Collectors.toList());
        itineraryView.showDtoList(itineraryResponseDtoList);

    }
}
