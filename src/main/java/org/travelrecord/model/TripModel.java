package org.travelrecord.model;

import org.travelrecord.dto.ResponseTripDTO;

import java.util.List;

public interface TripModel {

    public ResponseTripDTO save(ResponseTripDTO responseTripDTO);
    public List<ResponseTripDTO> JSONfindAll();
    public List<ResponseTripDTO> CSVfindAll();



}
