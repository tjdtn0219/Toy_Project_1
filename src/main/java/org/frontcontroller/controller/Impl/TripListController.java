package org.frontcontroller.controller.Impl;

import org.dto.TripDTO;
import org.model.Impl.ItineraryModelImpl;
import org.model.Impl.TripModelImpl;
import org.dto.ResponseItineraryDTO;
import org.dto.ResponseTripDTO;
import org.frontcontroller.Controller;
import org.view.TripView;

import java.util.ArrayList;
import java.util.List;

public class TripListController implements Controller {

    private TripModelImpl tripModelImpl = TripModelImpl.getInstance();
    private ItineraryModelImpl itineraryModelImpl = ItineraryModelImpl.getInstance();

    @Override
    public void process() {
//        System.out.println("여행 조회");
        TripView tripView = new TripView();

        List<ResponseTripDTO> responseTripDTOS = tripModelImpl.findAll();    //이 부분을 Model에서 만들어주세요.

        List<TripDTO.Response> responseList = new ArrayList<>();
        for (ResponseTripDTO responseTripDTO : responseTripDTOS) {
            List<ResponseItineraryDTO> itineraries = itineraryModelImpl.findAllByTripId(responseTripDTO.getId());    //이 부분을 Model에서 구현

            TripDTO.Response response = TripDTO.Response.fromEntity(responseTripDTO, itineraries);
            responseList.add(response);
        }

        tripView.showDtoList(responseList);

    }
}
