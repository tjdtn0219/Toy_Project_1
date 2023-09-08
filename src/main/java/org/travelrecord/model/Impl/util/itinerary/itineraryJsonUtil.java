package org.travelrecord.model.Impl.util.itinerary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.travelrecord.Entity.ItineraryEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class itineraryJsonUtil {

    public List<ItineraryEntity> readItineraryJsonFile(File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ItineraryEntity> itineraryEntities = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            JSONParser jsonParser = new JSONParser();
            Object object=  jsonParser.parse(br);
            if (object instanceof String) {
                JSONArray jsonArray = new JSONArray((String) object);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    ItineraryEntity itineraryEntity = new ItineraryEntity();
                    if (jsonObject.has("id")) { // "id" 키가 있는지 확인
                        itineraryEntity.setId((Integer) jsonObject.get("id"));
                    }

                    itineraryEntity.setTripId((Integer) jsonObject.get("tripId"));
                    itineraryEntity.setDeparturePlace((String) jsonObject.get("departurePlace"));
                    itineraryEntity.setDestination((String) jsonObject.get("destination"));

                    String departureTimeString = String.valueOf(jsonObject.get("departureTime"));
                    Date departureTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(departureTimeString);
                    itineraryEntity.setDepartureTime(departureTime);

                    String arrivalTimeString = String.valueOf(jsonObject.get("arrivalTime"));
                    Date arrivalTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(arrivalTimeString);
                    itineraryEntity.setArrivalTime(arrivalTime);

                    String checkInTimeString = String.valueOf(jsonObject.get("checkInTime"));
                    Date checkTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(checkInTimeString);
                    itineraryEntity.setCheckInTime(checkTime);

                    String checkOutTimeString = String.valueOf(jsonObject.get("checkOutTime"));
                    Date checkOutTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(checkOutTimeString);
                    itineraryEntity.setCheckOutTime(checkOutTime);

                    itineraryEntities.add(itineraryEntity);


                }
            }


            return itineraryEntities;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
