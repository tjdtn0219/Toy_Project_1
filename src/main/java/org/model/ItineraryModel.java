package org.model;

import org.dto.ResponseItineraryDTO;
import org.dto.ResponseTripDTO;

import java.util.List;

public interface ItineraryModel {
    public List<ResponseItineraryDTO> findAllByTripId(String fileName);
    public ResponseItineraryDTO save(Integer tripId,ResponseItineraryDTO responseItineraryDTO);
}
