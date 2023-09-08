package org.model.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.dto.ResponseItineraryDTO;
import org.dto.ResponseTripDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.model.Impl.util.itinerary.itineraryCsvUtil;
import org.model.Impl.util.itinerary.itineraryJsonUtil;
import org.model.ItineraryModel;
import java.io.*;
import java.util.*;

public class ItineraryModelImpl implements ItineraryModel {
    String baseFileName = "MyTrip_"; // Unique identifier
    String jsonPath = "src\\JSON"; //폴더 경로
    String csvPath = "src\\CSV"; //폴더 경로
    //C:\Users\s_sja\IdeaProjects\Toy-Project


    public List<ResponseItineraryDTO> findAllitineraryJsonByTripId(int tripId) {

        itineraryJsonUtil itineraryJsonUtil = new itineraryJsonUtil();
        List<ResponseItineraryDTO> itineraryDTO = new ArrayList<>();
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

    public List<ResponseItineraryDTO> findAllitineraryCsvByTripId(int tripId) {

        itineraryCsvUtil itineraryCsvUtil = new itineraryCsvUtil();
        List<ResponseItineraryDTO> itineraryDTO = new ArrayList<>();
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
    public ResponseItineraryDTO save(Integer tripId, ResponseItineraryDTO responseItineraryDTO) {
        itineraryCsvUtil itineraryCsvUtil = new itineraryCsvUtil();
        ResponseItineraryDTO savedResponseItineraryDTO = null;
        try {
            // JSON 폴더 경로 설정
            String jsonFolderPath = "C:\\Users\\82102\\Desktop\\JSON";
            String csvFolderPath = "C:\\Users\\82102\\Desktop\\CSV";
            File jsonFolder = new File(jsonFolderPath);
            File csvFolder = new File(csvFolderPath);

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
                responseDTO.put("id", responseItineraryDTO.getId());
                responseDTO.put("tripId", responseItineraryDTO.getTripId());
                responseDTO.put("departurePlace", responseItineraryDTO.getDeparturePlace());
                responseDTO.put("destination", responseItineraryDTO.getDestination());
                responseDTO.put("departureTime", responseItineraryDTO.getDepartureTime());
                responseDTO.put("arrivalTime", responseItineraryDTO.getArrivalTime());
                responseDTO.put("checkInTime", responseItineraryDTO.getCheckInTime());
                responseDTO.put("checkOutTime", responseItineraryDTO.getCheckOutTime());

                // 새로운 JSONObject를 JSON 배열에 추가
                responseDTOArray.put(responseDTO);


                try {
                    // 업데이트된 JSON 배열을 파일에 쓰기
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.writeValue(jsonFile, responseDTOArray.toString());
                    savedResponseItineraryDTO = responseItineraryDTO;
                    System.out.println("JSON 파일 및 csv 여정 저장 완료: " + jsonFile.getPath());


                } catch (IOException e) {
                    e.printStackTrace();
                }

                File csvFile = new File(csvtargetFolderPath, "MyItinerary.csv");
                itineraryCsvUtil.writeCsvData(csvFile, responseItineraryDTO);


                savedResponseItineraryDTO = responseItineraryDTO;
                System.out.println("CSV 여정 저장 완료: " + csvFile.getPath());
            } else {
                System.out.println("해당 tripId를 가진 폴더를 찾을 수 없습니다.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return savedResponseItineraryDTO;
    }


}