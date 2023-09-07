package org.frontcontroller.controller.Impl;

import org.dto.ItineraryDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.ItineraryModelImpl;
import org.dto.ResponseItineraryDTO;
import org.model.ItineraryModel;
import org.view.ItineraryView;

import java.util.List;

public class ItineraryListController implements Controller {

    private ItineraryModel itineraryModel = new ItineraryModelImpl();

    @Override
    public void process() {
//        System.out.println("여정 조회");

       /** ItineraryView itineraryView = new ItineraryView();

        int tripId = itineraryView.getTripIdForItineraries();
        List<ResponseItineraryDTO> itineraries = itineraryModel.findAllByTripId(responseTripDTO, tripId);  //이 부분을 Model에서 구현해주시면 됩니다.

        List<ItineraryDTO.Response> responseList = itineraries.stream()
                .map(ItineraryDTO.Response::fromEntity).toList();
        itineraryView.showDtoList(responseList);**/

    }
}
