package org.dto.respository;

import org.entity.Itinerary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ItineraryRepository {
    private static Map<Integer, Itinerary> store = new HashMap<>();
    private static int sequence = 0;

    private static ItineraryRepository instance = null;

    public static ItineraryRepository getInstance() {
        if(instance==null) {
            instance = new ItineraryRepository();
        }
        return instance;
    }
    private ItineraryRepository() { }

    public Itinerary save(Itinerary itinerary) {
        //실제 Model에서는 Trip을 저장할 때, 이미 만들어진 itinerary_<tripId>.json 파일에
        //itinerary.id를 +1한 후 저장하면 됩니다.
        itinerary.setId(++sequence);
        store.put(itinerary.getId(), itinerary);
        return itinerary;
    }

//    public Itinerary findById(Integer id) {
//        return store.get(id);
//    }

    public List<Itinerary> findAllByTripId(int tripId) {
        //이 부분을 Model에서 구현해주세요
        //실제 Model에서는 trip.getItineraryFileName()을 이용해서 해당 json파일의 값들을
        // List<Object>로 반환해주면 됩니다.
        return store.values().stream()
                .filter(itinerary -> itinerary.getTripId()==tripId)
                .collect(Collectors.toList());
    }

    public void clearStore() {
        store.clear();
    }
}
