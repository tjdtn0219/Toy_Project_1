package org.frontcontroller.controller.Impl;

import org.constant.FileType;
import org.dto.ItineraryDTO;
import org.dto.ResponseItineraryDTO;
import org.dto.ResponseTripDTO;
import org.dto.TripDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.ItineraryModelImpl;
import org.model.Impl.TripModelImpl;
import org.model.ItineraryModel;
import org.model.TripModel;
import org.view.ItineraryView;
import org.view.TripView;

import java.util.List;
import java.util.stream.Collectors;

import static org.constant.FileType.CSV;
import static org.constant.FileType.JSON;

public class ItineraryListController implements Controller {

    private ItineraryModel itineraryModel = new ItineraryModelImpl();
    private TripModel tripModel = new TripModelImpl();
    private ItineraryView itineraryView = new ItineraryView();
    private TripView tripView = new TripView();

    @Override
    public void process() {

        List<ResponseTripDTO> responseJSONTripDTOS = tripModel.JSONfindAll();
        tripView.showDtoList(
                responseJSONTripDTOS.stream().map(TripDTO.Response::fromEntity).toList()
        );

        int tripId = itineraryView.getTripIdForItineraries();

        FileType type = itineraryView.chooseFileType();
        List<ResponseItineraryDTO> itineraryList = null;

        if(type.equals(JSON)) {
            itineraryList = itineraryModel.findAllitineraryJsonByTripId(tripId);
        } else if(type.equals(CSV)) {
            itineraryList = itineraryModel.findAllitineraryCsvByTripId(tripId);
        }

        List<ItineraryDTO.Response> responseList = itineraryList.stream()
                .map(ItineraryDTO.Response::fromEntity).collect(Collectors.toList());
        itineraryView.showDtoList(responseList);

    }
}
