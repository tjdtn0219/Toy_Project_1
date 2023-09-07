package org.model.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.dto.ResponseItineraryDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.model.ItineraryModel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class ItineraryModelImpl implements ItineraryModel {


    public List<ResponseItineraryDTO> findAllByTripId(String tripPath) {

        File itineraryFile = new File(StringUtils.join(tripPath,"/MyItinerary.json"));

        if (!itineraryFile.exists()) {
            System.out.println("디렉터리가 존재하지 않습니다: " + itineraryFile);
            return Collections.emptyList();
        }

        List<ResponseItineraryDTO> itineraryDTO = readItineraryJsonFile(itineraryFile);

        return itineraryDTO;

    }

    private List<ResponseItineraryDTO> readItineraryJsonFile(File file) {
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
                    responseItineraryDTO.setArrivalTime(checkTime);
                    String checkOutTimeString = String.valueOf(jsonObject.get("checkOutTime"));
                    Date checkOutTime = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US).parse(checkOutTimeString);
                    responseItineraryDTO.setArrivalTime(checkOutTime);
                    responseItineraryDTOS.add(responseItineraryDTO);

                    System.out.println(responseItineraryDTO.toString());
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

    @Override
    public ResponseItineraryDTO save(Integer tripId, ResponseItineraryDTO responseItineraryDTO) {
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
                if (folderName.startsWith("MyTrip") && folderName.length() > "MyTrip".length()) {
                    String tripIdStr = folderName.substring("MyTrip".length());
                    if (tripIdStr.equals(String.valueOf(tripId))) {
                        jsontargetFolderPath = subFolder.getAbsolutePath();
                        break; // 폴더를 찾았으면 반복문 종료
                    }
                }
            }

            for (File subFolder : subcsvFolders) {
                String folderName = subFolder.getName();
                if (folderName.startsWith("MyTrip") && folderName.length() > "MyTrip".length()) {
                    String tripIdStr = folderName.substring("MyTrip".length());
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
                writeCsvData(csvFile, responseItineraryDTO);


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

    private void writeCsvData(File csvFile, ResponseItineraryDTO responseItineraryDTO) throws IOException {
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