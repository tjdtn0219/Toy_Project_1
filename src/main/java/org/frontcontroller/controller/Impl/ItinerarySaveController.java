package org.frontcontroller.controller.Impl;

import org.dto.ItineraryDTO;
import org.dto.ResponseTripDTO;
import org.dto.TripDTO;
import org.frontcontroller.controller.Controller;
import org.model.Impl.ItineraryModelImpl;
import org.dto.ResponseItineraryDTO;
import org.model.Impl.TripModelImpl;
import org.model.TripModel;
import org.view.ItineraryView;
import org.view.TripView;

import java.text.ParseException;
import java.util.List;

public class ItinerarySaveController implements Controller {

    private ItineraryModelImpl itineraryModel = new ItineraryModelImpl();
    private TripModel tripModel = new TripModelImpl();
    private ItineraryView itineraryView = new ItineraryView();
    private TripView tripView = new TripView();

    @Override
    public void process() throws ParseException {
//        System.out.println("여정 기록");
        //여행리스트 먼저 출력 -> 이후 뷰에서는 이 중 어떤 여행을 선택하시겠습니까 멘트 후 입력받은 숫자를 통해 해당 여행에 여정 기록

        List<ResponseTripDTO> responseJSONTripDTOS = tripModel.JSONfindAll();
        tripView.showDtoList(
                responseJSONTripDTOS.stream().map(TripDTO.Response::fromEntity).toList()
        );

        ItineraryDTO.Request request = itineraryView.getDtoFromInput();

        ResponseItineraryDTO responseItineraryDTO = ResponseItineraryDTO.builder()
                .tripId(request.getTripId())
                .departurePlace(request.getDeparturePlace())
                .destination(request.getDestination())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .checkInTime(request.getCheckInTime())
                .checkOutTime(request.getCheckOutTime())
                .build();

       ResponseItineraryDTO savedResponseItineraryDTO = itineraryModel.save(request.getTripId(),responseItineraryDTO);

        System.out.println("**저장 내역**\n" + savedResponseItineraryDTO + "\n");
    }
}
