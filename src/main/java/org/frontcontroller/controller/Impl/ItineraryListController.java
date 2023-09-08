package org.frontcontroller.controller.Impl;

import org.dto.ItineraryDTO;
import org.dto.ResponseTripDTO;
import org.dto.TripDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.ItineraryModelImpl;
import org.dto.ResponseItineraryDTO;
import org.model.Impl.TripModelImpl;
import org.model.ItineraryModel;
import org.model.TripModel;
import org.view.ItineraryView;
import org.view.TripView;

import java.util.List;

public class ItineraryListController implements Controller {

    private ItineraryModel itineraryModel = new ItineraryModelImpl();
    private TripModel tripModel = new TripModelImpl();
    private ItineraryView itineraryView = new ItineraryView();
    private TripView tripView = new TripView();

    @Override
    public void process() {
//        System.out.println("여정 조회");

        List<ResponseTripDTO> responseJSONTripDTOS = tripModel.JSONfindAll();
        tripView.showDtoList(
                responseJSONTripDTOS.stream().map(TripDTO.Response::fromEntity).toList()
        );

        int tripId = itineraryView.getTripIdForItineraries(); //찾을 여정 이전에 여행 번호 먼저 입력

        // 폴더에 있는 모든 여행리스트를 가져옴,필요에 따라 JSON,CSV파일 받아서 해당 메소드들 호출해주시면 됩니다.
        //  List<ResponseItineraryDTO> jsonItineraries = itineraryModel.findAllitineraryJsonByTripId(tripId);
        // CSV값 들어오면 해당 메소드 출력
        List<ResponseItineraryDTO> csvItineraries = itineraryModel.findAllitineraryCsvByTripId(tripId);

      /**  List<ItineraryDTO.Response> jsonResponseList = jsonItineraries.stream()
                .map(ItineraryDTO.Response::fromEntity).toList();
        itineraryView.showDtoList(jsonResponseList);**/


        List<ItineraryDTO.Response> csvResponseList = csvItineraries.stream()
                .map(ItineraryDTO.Response::fromEntity).toList();
        itineraryView.showDtoList(csvResponseList);

    }
}
