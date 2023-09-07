package org.model;

import org.dto.ResponseItineraryDTO;
import org.dto.ResponseTripDTO;

import java.util.List;

public interface ItineraryModel {
    public List<ResponseItineraryDTO> findAllitineraryJsonByTripId(int tripId);
    public List<ResponseItineraryDTO> findAllitineraryCsvByTripId(int tripId);
    public ResponseItineraryDTO save(Integer tripId,ResponseItineraryDTO responseItineraryDTO);
}
