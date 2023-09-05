package org.dto.respository;

import org.entity.Trip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripRepository {
    private static Map<Integer, Trip> store = new HashMap<>();
    private static int sequence = 0;

    private static TripRepository instance = null;

    public static TripRepository getInstance() {
        if(instance==null) {
            instance = new TripRepository();
        }
        return instance;
    }
    private TripRepository() { }

    public Trip save(Trip trip) {
        trip.setId(++sequence);
        trip.setItineraryFileName("itinerary_" + sequence); //실제 Model에서는 itinerary_<key>.json 파일 자동으로 생성
        store.put(trip.getId(), trip);
        return trip;
    }

//    public Trip findById(Integer id) {
//        return store.get(id);
//    }

    public List<Trip> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
