package org.model;

import org.dto.ResponseTripDTO;

import java.util.List;

public interface TripModel {

    public ResponseTripDTO save(ResponseTripDTO responseTripDTO);
    public List<ResponseTripDTO> findAll();
    public int findHighestNumber(String path, String baseFileName);

}
