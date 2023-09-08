package org.travelrecord.model;

import org.travelrecord.Entity.ItineraryEntity;

import java.util.List;

public interface ItineraryModel {
    public List<ItineraryEntity> findAllitineraryJsonByTripId(int tripId);
    public List<ItineraryEntity> findAllitineraryCsvByTripId(int tripId);
    public ItineraryEntity save(Integer tripId, ItineraryEntity itineraryEntity);
}
