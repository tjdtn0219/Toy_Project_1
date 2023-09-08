package org.travelrecord.model.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.travelrecord.Entity.ItineraryEntity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.travelrecord.model.Impl.util.LoadPath;
import org.travelrecord.model.Impl.util.itinerary.itineraryCsvUtil;
import org.travelrecord.model.Impl.util.itinerary.itineraryJsonUtil;
import org.travelrecord.model.ItineraryModel;
import java.io.*;
import java.util.*;

public class ItineraryModelImpl implements ItineraryModel {
    String baseFileName = "MyTrip_"; // Unique identifier

    String jsonPath, csvPath;

    public ItineraryModelImpl() {
        LoadPath loadPath = new LoadPath();
        // 파일 경로 값 properties 에서 가져와 적용
        jsonPath = loadPath.getJsonPath();
        csvPath = loadPath.getCsvPath();
    }

    public List<ItineraryEntity> findAllitineraryJsonByTripId(int tripId) {

        itineraryJsonUtil itineraryJsonUtil = new itineraryJsonUtil();
        List<ItineraryEntity> itineraryDTO = new ArrayList<>();
        String tripPath = baseFileName + tripId;
        File directory = new File(jsonPath);
        File[] files = directory.listFiles();
       
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory() && file.getName().equals(tripPath)) {
                    File itineraryFile = new File(StringUtils.join(jsonPath,"\\",tripPath,"/MyItinerary.json"));

                    if (!itineraryFile.exists()) {
                        System.out.println("디렉터리가 존재하지 않습니다: " + itineraryFile);
                        return Collections.emptyList();
                    }
                  
                    itineraryDTO = itineraryJsonUtil.readItineraryJsonFile(itineraryFile);

                }
            }
        }

        return itineraryDTO;

    }

    public List<ItineraryEntity> findAllitineraryCsvByTripId(int tripId) {

        itineraryCsvUtil itineraryCsvUtil = new itineraryCsvUtil();
        List<ItineraryEntity> itineraryDTO = new ArrayList<>();
        String tripPath = baseFileName + tripId;
        File directory = new File(jsonPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory() && file.getName().equals(tripPath)) {
                    File itineraryFile = new File(StringUtils.join(csvPath,"\\",tripPath,"/MyItinerary.csv"));

                    if (!itineraryFile.exists()) {
                        System.out.println("디렉터리가 존재하지 않습니다: " + itineraryFile);
                        return Collections.emptyList();
                    }

                    itineraryDTO = itineraryCsvUtil.readItineraryCsvFile(itineraryFile);

                }
            }
        }

        return itineraryDTO;

    }


    @Override
    public ItineraryEntity save(Integer tripId, ItineraryEntity itineraryEntity) {
        itineraryCsvUtil itineraryCsvUtil = new itineraryCsvUtil();
        ItineraryEntity savedItineraryEntity = null;
        try {
            // 폴더 경로 설정
            File jsonFolder = new File(jsonPath);
            File csvFolder = new File(csvPath);

            // JSON 폴더 내의 모든 폴더를 가져옴
            File[] subjsonFolders = jsonFolder.listFiles(File::isDirectory);
            File[] subcsvFolders = csvFolder.listFiles(File::isDirectory);

            // "Mytrip"으로 시작하고 tripId와 일치하는 폴더를 찾음
            String jsontargetFolderPath = null;
            String csvtargetFolderPath = null;

            for (File subFolder : subjsonFolders) {
                String folderName = subFolder.getName();
                if (folderName.startsWith("MyTrip_") && folderName.length() > "MyTrip_".length()) {
                    String tripIdStr = folderName.substring("MyTrip_".length());
                    if (tripIdStr.equals(String.valueOf(tripId))) {
                        jsontargetFolderPath = subFolder.getAbsolutePath();
                        break; // 폴더를 찾았으면 반복문 종료
                    }
                }
            }

            for (File subFolder : subcsvFolders) {
                String folderName = subFolder.getName();
                if (folderName.startsWith("MyTrip_") && folderName.length() > "MyTrip_".length()) {
                    String tripIdStr = folderName.substring("MyTrip_".length());
                    if (tripIdStr.equals(String.valueOf(tripId))) {
                        csvtargetFolderPath = subFolder.getAbsolutePath();
                        break; // 폴더를 찾았으면 반복문 종료
                    }
                }
            }


            // targetFolderPath가 null이 아니라면 해당 폴더 내에 MyItinerary.json 파일을 생성
            if (jsontargetFolderPath != null && csvtargetFolderPath != null) {
                JSONArray responseDTOArray = new JSONArray();
                File jsonFile = new File(jsontargetFolderPath, "MyItinerary.json");

                if (jsonFile.exists()) {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        responseDTOArray = objectMapper.readValue(jsonFile, new TypeReference<JSONArray>() {});
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                JSONObject responseDTO = new JSONObject();

                // 새로운 데이터를 JSONObject로 변환하여 추가
                responseDTO.put("id", itineraryEntity.getId());
                responseDTO.put("tripId", itineraryEntity.getTripId());
                responseDTO.put("departurePlace", itineraryEntity.getDeparturePlace());
                responseDTO.put("destination", itineraryEntity.getDestination());
                responseDTO.put("departureTime", itineraryEntity.getDepartureTime());
                responseDTO.put("arrivalTime", itineraryEntity.getArrivalTime());
                responseDTO.put("checkInTime", itineraryEntity.getCheckInTime());
                responseDTO.put("checkOutTime", itineraryEntity.getCheckOutTime());

                // 새로운 JSONObject를 JSON 배열에 추가
                responseDTOArray.put(responseDTO);


                try {
                    // 업데이트된 JSON 배열을 파일에 쓰기
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writeValue(jsonFile, responseDTOArray.toString());
                    savedItineraryEntity = itineraryEntity;



                } catch (IOException e) {
                    e.printStackTrace();
                }

                File csvFile = new File(csvtargetFolderPath, "MyItinerary.csv");
                itineraryCsvUtil.writeCsvData(csvFile, itineraryEntity);


                savedItineraryEntity = itineraryEntity;

            } else {
                System.out.println("해당 tripId를 가진 폴더를 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return savedItineraryEntity;
    }


}