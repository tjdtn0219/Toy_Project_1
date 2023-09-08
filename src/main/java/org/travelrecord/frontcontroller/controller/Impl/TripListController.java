package org.travelrecord.frontcontroller.controller.Impl;

import org.travelrecord.constant.FileType;
import org.travelrecord.dto.ResponseTripDTO;
import org.travelrecord.dto.TripDTO;
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
        List<ResponseTripDTO> tripList = null;

        if(type.equals(JSON)) {
            tripList = tripModel.JSONfindAll();
        } else if (type.equals(CSV)) {
            tripList = tripModel.CSVfindAll();
        }

        List<TripDTO.Response> responseList = tripList.stream()
                .map(TripDTO.Response::fromEntity).collect(Collectors.toList());
        tripView.showDtoList(responseList);

    }
}
