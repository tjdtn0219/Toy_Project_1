package org.travelrecord.model.Impl.util.itinerary;

import org.travelrecord.Entity.ItineraryEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class itineraryCsvUtil {

    public List<ItineraryEntity> readItineraryCsvFile(File file) {

        List<ItineraryEntity> itineraryEntities = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            if (line != null) {
                String[] data = line.split(",");

                if (data.length == 7) { // 필드 개수에 맞게 조정

                    ItineraryEntity itineraryEntity = new ItineraryEntity();
                    itineraryEntity.setId(Integer.parseInt(data[0].replace("\uFEFF", "")));
                    itineraryEntity.setDeparturePlace(data[1]);
                    itineraryEntity.setDestination(data[2]);

                    String departureTimeString = String.valueOf(data[3]);
                    Date departureTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(departureTimeString);
                    itineraryEntity.setDepartureTime(departureTime);

                    String arrivalTimeString = String.valueOf(data[4]);
                    Date arrivalTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(arrivalTimeString);
                    itineraryEntity.setArrivalTime(arrivalTime);

                    String checkInTimeString = String.valueOf(data[5]);
                    Date checkInTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(checkInTimeString);
                    itineraryEntity.setCheckInTime(checkInTime);

                    String checkOutTimeString = String.valueOf(data[6]);
                    Date checkOutTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(checkOutTimeString);
                    itineraryEntity.setCheckOutTime(checkOutTime);

                    itineraryEntities.add(itineraryEntity);



                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        }
        return itineraryEntities;

    }
    public void writeCsvData(File csvFile, ItineraryEntity itineraryEntity) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(csvFile);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {
            // Write BOM.
            writer.write('\ufeff');

            // 여정 데이터를 CSV 파일에 추가
            String[] tempCsvInfo = new String[7];
            tempCsvInfo[0] = String.valueOf(itineraryEntity.getTripId());
            tempCsvInfo[1] = itineraryEntity.getDeparturePlace();
            tempCsvInfo[2] = itineraryEntity.getDestination();
            tempCsvInfo[3] = String.valueOf(itineraryEntity.getDepartureTime());
            tempCsvInfo[4] = String.valueOf(itineraryEntity.getArrivalTime());
            tempCsvInfo[5] = String.valueOf(itineraryEntity.getCheckInTime());
            tempCsvInfo[6] = String.valueOf(itineraryEntity.getCheckOutTime());

            writer.write(String.join(",",tempCsvInfo));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
