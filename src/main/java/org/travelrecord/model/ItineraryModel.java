package org.travelrecord.model;

import org.travelrecord.dto.ResponseItineraryDTO;

import java.util.List;

public interface ItineraryModel {
    public List<ResponseItineraryDTO> findAllitineraryJsonByTripId(int tripId);
    public List<ResponseItineraryDTO> findAllitineraryCsvByTripId(int tripId);
    public ResponseItineraryDTO save(Integer tripId,ResponseItineraryDTO responseItineraryDTO);
}
