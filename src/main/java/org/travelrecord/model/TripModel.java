package org.travelrecord.model;

import org.travelrecord.Entity.TripEntity;

import java.util.List;

public interface TripModel {

    public TripEntity save(TripEntity tripEntity);
    public List<TripEntity> JSONfindAll();
    public List<TripEntity> CSVfindAll();



}
