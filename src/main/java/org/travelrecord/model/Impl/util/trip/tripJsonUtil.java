package org.travelrecord.model.Impl.util.trip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.travelrecord.Entity.TripEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class tripJsonUtil {

    public List<TripEntity> findAllJsonFiles(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            System.out.println("디렉터리가 존재하지 않습니다: " + directoryPath);
            return Collections.EMPTY_LIST;
        }

        File[] files = directory.listFiles();
        List<TripEntity> tripList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    tripList.addAll(findAllJsonFiles(file.getAbsolutePath()));

                } else if (file.isFile() && file.getName().startsWith("MyTrip_")) {
                    // JSON 파일인 경우 읽고 ResponseTripDTO로 파싱하여 리스트에 추가
                    TripEntity tripDTO = readTripJsonFile(file);
                    tripList.add(tripDTO);
                    if (tripDTO != null) {
                        tripDTO.setDirPath(file.getParent());
                    }
                }
            }
        }
        return tripList;
    }

    public TripEntity readTripJsonFile(File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file, TripEntity.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveDTOAsJson(String filePath, TripEntity tripEntity) {
        try {
            // Jackson ObjectMapper 초기화
            ObjectMapper objectMapper = new ObjectMapper();

            // DTO 객체를 JSON 문자열로 변환
            String tripJson = objectMapper.writeValueAsString(tripEntity);

            // JSON 파일로 저장
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(tripJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
