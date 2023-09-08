package org.frontcontroller.controller.Impl;

import org.constant.FileType;
import org.dto.ResponseTripDTO;
import org.dto.TripDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.ItineraryModelImpl;
import org.model.Impl.TripModelImpl;
import org.model.ItineraryModel;
import org.model.TripModel;
import org.view.TripView;

import java.util.List;
import java.util.stream.Collectors;

import static org.constant.FileType.CSV;
import static org.constant.FileType.JSON;

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
