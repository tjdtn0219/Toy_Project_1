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

        // 폴더에 있는 모든 여행리스트를 가져옴,필요에 따라 JSON,CSV파일 받아서 해당 메소드들 호출해주시면 됩니다.
        List<ResponseTripDTO> responseJSONTripDTOS = tripModel.JSONfindAll();
        List<ResponseTripDTO> responseCSVTripDTOS = tripModel.CSVfindAll();

        System.out.println(responseJSONTripDTOS.toString());
        System.out.println(responseCSVTripDTOS.toString());
      
        //해당 부분 필요없어서 삭제해도 될 것 같음,tripView.showDTOlist 부분은 위에 responseJsonTrip이나csv 리스트 넣어서 출력
      /**  List<TripDTO.Response> responseList = new ArrayList<>();
        for (ResponseTripDTO responseTripDTO : responseJSONTripDTOS) {
            List<ResponseItineraryDTO> itineraries = itineraryModel.findAllByTripId(responseTripDTO.getDirPath());

            TripDTO.Response response = TripDTO.Response.fromEntity(responseTripDTO, itineraries);
            responseList.add(response);
        }

        tripView.showDtoList(responseList);**/

    }
}
