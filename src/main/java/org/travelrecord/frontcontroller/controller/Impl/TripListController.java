package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.constant.FileType;
import org.travelrecord.Entity.TripEntity;
import org.travelrecord.dto.requestTripDTO;
import org.travelrecord.frontcontroller.controller.Controller;
import org.travelrecord.model.Impl.ItineraryModelImpl;
import org.travelrecord.model.Impl.TripModelImpl;
import org.travelrecord.model.ItineraryModel;
import org.travelrecord.model.TripModel;
import org.travelrecord.view.TripView;

import java.util.List;
import java.util.stream.Collectors;

import static org.travelrecord.constant.FileType.CSV;
import static org.travelrecord.constant.FileType.JSON;

public class TripListController implements Controller {

    private TripModel tripModel = new TripModelImpl();
    private ItineraryModel itineraryModel = new ItineraryModelImpl();
    private TripView tripView = new TripView();

    @Override
    public void process() {

        FileType type = tripView.chooseFileType();
        List<TripEntity> tripList = null;

        if(type.equals(JSON)) {
            tripList = tripModel.JSONfindAll();
        } else if (type.equals(CSV)) {
            tripList = tripModel.CSVfindAll();
        }

        List<requestTripDTO.Response> responseList = tripList.stream()
                .map(requestTripDTO.Response::fromEntity).collect(Collectors.toList());
        tripView.showDtoList(responseList);

    }
}
