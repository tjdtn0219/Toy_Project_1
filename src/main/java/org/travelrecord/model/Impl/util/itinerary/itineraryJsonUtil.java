package org.travelrecord.model.Impl.util.itinerary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.travelrecord.dto.ResponseItineraryDTO;
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

    public List<ResponseItineraryDTO> readItineraryJsonFile(File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<ResponseItineraryDTO> responseItineraryDTOS = new ArrayList<>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            JSONParser jsonParser = new JSONParser();
            Object object=  jsonParser.parse(br);
            if (object instanceof String) {
                JSONArray jsonArray = new JSONArray((String) object);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                    ResponseItineraryDTO responseItineraryDTO = new ResponseItineraryDTO();
                    if (jsonObject.has("id")) { // "id" 키가 있는지 확인
                        responseItineraryDTO.setId((Integer) jsonObject.get("id"));
                    }

                    responseItineraryDTO.setTripId((Integer) jsonObject.get("tripId"));
                    responseItineraryDTO.setDeparturePlace((String) jsonObject.get("departurePlace"));
                    responseItineraryDTO.setDestination((String) jsonObject.get("destination"));

                    String departureTimeString = String.valueOf(jsonObject.get("departureTime"));
                    Date departureTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(departureTimeString);
                    responseItineraryDTO.setDepartureTime(departureTime);

                    String arrivalTimeString = String.valueOf(jsonObject.get("arrivalTime"));
                    Date arrivalTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(arrivalTimeString);
                    responseItineraryDTO.setArrivalTime(arrivalTime);

                    String checkInTimeString = String.valueOf(jsonObject.get("checkInTime"));
                    Date checkTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(checkInTimeString);
                    responseItineraryDTO.setCheckInTime(checkTime);

                    String checkOutTimeString = String.valueOf(jsonObject.get("checkOutTime"));
                    Date checkOutTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(checkOutTimeString);
                    responseItineraryDTO.setCheckOutTime(checkOutTime);

                    responseItineraryDTOS.add(responseItineraryDTO);


                }
            }


            return responseItineraryDTOS;
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
