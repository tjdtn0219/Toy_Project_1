package org.frontcontroller.controller;

import org.dto.ResponseItineraryDTO;
import org.dto.ResponseTripDTO;
import org.dto.TripDTO;
import org.model.Impl.ItineraryModelImpl;
import org.model.Impl.TripModelImpl;
import org.model.ItineraryModel;
import org.model.TripModel;
import org.view.TripView;

import java.util.ArrayList;
import java.util.List;

public class TripListController implements Controller {

    private TripModel tripModelimpl = new TripModelImpl();
    private ItineraryModel itineraryimpl = new ItineraryModelImpl();

    @Override
    public void process() {
//        System.out.println("여행 조회");
        TripView tripView = new TripView();

        List<ResponseTripDTO> trips = tripModelimpl.findAll();

        List<TripDTO.Response> responseList = new ArrayList<>();
        for (ResponseTripDTO trip : trips) {
            List<ResponseItineraryDTO> itineraries = itineraryimpl.findAllByTripId(trip.getDirPath());
            //이 부분을 Model에서 구현

            TripDTO.Response response = TripDTO.Response.fromEntity(trip, itineraries);
            responseList.add(response);
        }

        tripView.showDtoList(responseList);

    }
}
