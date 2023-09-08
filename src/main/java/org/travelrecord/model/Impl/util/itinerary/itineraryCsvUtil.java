package org.travelrecord.model.Impl.util.itinerary;

import org.travelrecord.dto.ResponseItineraryDTO;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class itineraryCsvUtil {

    public List<ResponseItineraryDTO> readItineraryCsvFile(File file) {

        List<ResponseItineraryDTO> responseItineraryDTOS = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            if (line != null) {
                String[] data = line.split(",");

                if (data.length == 7) { // 필드 개수에 맞게 조정

                    ResponseItineraryDTO responseItineraryDTO = new ResponseItineraryDTO();
                    responseItineraryDTO.setId(Integer.parseInt(data[0].replace("\uFEFF", "")));
                    responseItineraryDTO.setDeparturePlace(data[1]);
                    responseItineraryDTO.setDestination(data[2]);

                    String departureTimeString = String.valueOf(data[3]);
                    Date departureTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(departureTimeString);
                    responseItineraryDTO.setDepartureTime(departureTime);

                    String arrivalTimeString = String.valueOf(data[4]);
                    Date arrivalTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(arrivalTimeString);
                    responseItineraryDTO.setArrivalTime(arrivalTime);

                    String checkInTimeString = String.valueOf(data[5]);
                    Date checkInTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(checkInTimeString);
                    responseItineraryDTO.setCheckInTime(checkInTime);

                    String checkOutTimeString = String.valueOf(data[6]);
                    Date checkOutTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(checkOutTimeString);
                    responseItineraryDTO.setCheckOutTime(checkOutTime);

                    responseItineraryDTOS.add(responseItineraryDTO);



                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
        return responseItineraryDTOS;

    }
    public void writeCsvData(File csvFile, ResponseItineraryDTO responseItineraryDTO) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(csvFile);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {
            // Write BOM.
            writer.write('\ufeff');

            // 여정 데이터를 CSV 파일에 추가
            String[] tempCsvInfo = new String[7];
            tempCsvInfo[0] = String.valueOf(responseItineraryDTO.getTripId());
            tempCsvInfo[1] = responseItineraryDTO.getDeparturePlace();
            tempCsvInfo[2] = responseItineraryDTO.getDestination();
            tempCsvInfo[3] = String.valueOf(responseItineraryDTO.getDepartureTime());
            tempCsvInfo[4] = String.valueOf(responseItineraryDTO.getArrivalTime());
            tempCsvInfo[5] = String.valueOf(responseItineraryDTO.getCheckInTime());
            tempCsvInfo[6] = String.valueOf(responseItineraryDTO.getCheckOutTime());

            writer.write(String.join(",",tempCsvInfo));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
