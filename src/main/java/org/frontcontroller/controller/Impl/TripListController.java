package org.frontcontroller.controller.Impl;

import org.dto.TripDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.ItineraryModelImpl;
import org.model.Impl.TripModelImpl;
import org.dto.ResponseItineraryDTO;
import org.dto.ResponseTripDTO;
import org.model.ItineraryModel;
import org.model.TripModel;
import org.view.TripView;

import java.util.ArrayList;
import java.util.List;

public class TripListController implements Controller {

    private TripModel tripModel = new TripModelImpl();
    private ItineraryModel itineraryModel = new ItineraryModelImpl();

    @Override
    public void process() {
//        System.out.println("여행 조회");
        TripView tripView = new TripView();

         // 폴더에 있는 모든 여행리스트를 가져옴
        List<ResponseTripDTO> responseTripDTOS = tripModel.findAll();
        System.out.println(responseTripDTOS.toString());

          List<TripDTO.Response> responseList = new ArrayList<>();
          for (ResponseTripDTO responseTripDTO : responseTripDTOS) {
            List<ResponseItineraryDTO> itineraries = itineraryModel.findAllByTripId(responseTripDTO.getDirPath());

            TripDTO.Response response = TripDTO.Response.fromEntity(responseTripDTO, itineraries);
            responseList.add(response);
        }

        tripView.showDtoList(responseList);

    }
}
