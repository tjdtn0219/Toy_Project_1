package org.travelrecord.model.Impl.util.trip;

import org.travelrecord.Entity.TripEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class tripCsvUtil {

    public List<TripEntity> findAllCsvFiles(String directoryPath) {
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
                    tripList.addAll(findAllCsvFiles(file.getAbsolutePath()));

                } else if (file.isFile() && file.getName().startsWith("MyTrip_")) {
                    // CSV 파일인 경우 읽고 ResponseTripDTO로 파싱하여 리스트에 추가
                    TripEntity tripDTO = readTripCsvFile(file);
                    tripList.add(tripDTO);
                    if (tripDTO != null) {
                        tripDTO.setDirPath(file.getParent());
                    }
                }
            }
        }
        return tripList;
    }

    private TripEntity readTripCsvFile(File file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "MS949"))) {
            String line = reader.readLine();
            if (line != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // 필드 개수에 맞게 조정
                    TripEntity tripDTO = new TripEntity();
                    tripDTO.setId(Integer.parseInt(data[0].replaceAll("\\]", "").trim()));
                    tripDTO.setTripName(data[1].trim());

                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    Date startDate = dateFormat.parse(data[2].trim());
                    tripDTO.setStartDate(startDate);

                    Date endDate = dateFormat.parse(data[3].trim());
                    tripDTO.setEndDate(endDate);

                    tripDTO.setDirPath(data[4].replaceAll("\\]", "").trim());
                    return tripDTO;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void saveDTOAsCsv(String filePath, TripEntity tripEntity) {
        try {
            // CSV 파일 경로 설정 (폴더 내)
            File csvFile = new File(filePath);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "MS949"))) {

                // ResponseTripDTO 객체가 null이 아닌지 확인
                if (tripEntity != null) {
                    // ResponseTripDTO 객체를 CSV 문자열로 변환
                    String[] csvData = convertTripDTOToCsv(tripEntity);

                    StringBuilder sb = new StringBuilder();
                    for (String data : csvData) {
                        sb.append(data).append(",");
                    }

                    // 마지막 쉼표 제거
                    if (sb.length() > 0) {
                        sb.setLength(sb.length() - 1);
                    }

                    // CSV 파일에 데이터 쓰기
                    bw.write(sb.toString());


                } else {
                    System.out.println("ResponseTripDTO 객체가 null입니다.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] convertTripDTOToCsv(TripEntity tripEntity) {
        String[] csvData = new String[5]; // 필드 개수에 맞게 배열 크기 조정

        if (tripEntity.getId() != null) {
            csvData[0] = tripEntity.getId().toString();
        } else {
            // ID가 null인 경우에 대한 처리
            csvData[0] = "0";
        }

        csvData[1] = new String(tripEntity.getTripName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        csvData[2] = formatDate(tripEntity.getStartDate());
        csvData[3] = formatDate(tripEntity.getEndDate());
        if (tripEntity.getDirPath() != null) {
            new String(tripEntity.getDirPath().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } else {
            // 파일 이름이 null인 경우
            csvData[4] = "null";
        }


        return csvData;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
