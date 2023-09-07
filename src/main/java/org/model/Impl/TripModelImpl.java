package org.model.Impl;


import org.dto.ResponseTripDTO;
import org.model.TripModel;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;


public class TripModelImpl implements TripModel {
    String baseFileName = "MyTrip"; // Unique identifier
    String jsonfileExtension = ".json"; // json파일 확장자
    String csvfileExtension = ".csv"; // csv파일 확장자
    String jsonPath = "C:\\Users\\82102\\Desktop\\JSON"; //폴더 경로
    String csvPath = "C:\\Users\\82102\\Desktop\\CSV"; //폴더 경로

    public ResponseTripDTO save(ResponseTripDTO responseTripDTO) {
        try {
            int highestJsonNumber = findHighestNumber(jsonPath, baseFileName);
            int highestCsvNumber = findHighestNumber(csvPath, baseFileName);

            // 폴더명 생성
            String jsonFolderName = baseFileName + (highestJsonNumber + 1);
            String csvFolderName = baseFileName + (highestCsvNumber + 1);

            // JSON 폴더 생성
            createFolderIfNotExists(jsonPath, jsonFolderName);



            String jsonFilePath = jsonPath + File.separator + jsonFolderName + File.separator + jsonFolderName + jsonfileExtension;
            String csvFilePath = csvPath + File.separator + csvFolderName + File.separator + csvFolderName + csvfileExtension;

            // CSV 폴더 생성
            createFolderIfNotExists(csvPath, csvFolderName);

            // JSON 파일 생성
            saveDTOAsJson(jsonFilePath, responseTripDTO);

            saveDTOAsCsv(csvFilePath, responseTripDTO);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseTripDTO;
    }

    @Override
    public List<ResponseTripDTO> findAll() {

        List<ResponseTripDTO> tripList = findAllJsonFiles(jsonPath);
        return tripList;
    }

    private List<ResponseTripDTO> findAllJsonFiles(String directoryPath) {
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            System.out.println("디렉터리가 존재하지 않습니다: " + directoryPath);
            return Collections.EMPTY_LIST;
        }

        File[] files = directory.listFiles();
        List<ResponseTripDTO> tripList = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    tripList.addAll(findAllJsonFiles(file.getAbsolutePath()));

                } else if (file.isFile() && file.getName().startsWith("MyTrip")) {
                    // JSON 파일인 경우 읽고 ResponseTripDTO로 파싱하여 리스트에 추가
                        ResponseTripDTO tripDTO = readTripJsonFile(file);
                        tripList.add(tripDTO);
                        if (tripDTO != null) {
                            tripDTO.setDirPath(file.getParent());
                        }
                }
            }
        }
        return tripList;
    }



    private ResponseTripDTO readTripJsonFile(File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            System.out.println(file.getPath());
            return objectMapper.readValue(file, ResponseTripDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    public int findHighestNumber(String path, String baseFileName) {
        int highestNumber = 0;

        File currentDirectory = new File(path);
        File[] files = currentDirectory.listFiles();

        for (File file : files) {
            if (file.isDirectory() && file.getName().startsWith(baseFileName)) {
                String folderName = file.getName().replace(baseFileName, "");
                try {
                    int number = Integer.parseInt(folderName);
                    if (number > highestNumber) {
                        highestNumber = number;
                    }
                } catch (NumberFormatException e) {
                    // 숫자로 변환할 수 없는 폴더 이름은 무시
                }
            }
        }

        return highestNumber;
    }

    private void createFolderIfNotExists(String path, String folderName) {
        File folder = new File(path + File.separator + folderName);
        if (!folder.exists()) {
            folder.mkdir();
            System.out.println("폴더가 생성되었습니다: " + folder.getPath());
        } else {
            System.out.println("이미 폴더가 생성되어 있습니다: " + folder.getPath());
        }
    }

    private void saveDTOAsJson(String filePath, ResponseTripDTO responseTripDTO) {
        try {
            // Jackson ObjectMapper 초기화
            ObjectMapper objectMapper = new ObjectMapper();

            // DTO 객체를 JSON 문자열로 변환
            String tripJson = objectMapper.writeValueAsString(responseTripDTO);

            // JSON 파일로 저장
            try (FileWriter writer = new FileWriter(filePath)) {
                writer.write(tripJson);
                System.out.println("JSON 파일 저장 완료: " + filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveDTOAsCsv(String filePath, ResponseTripDTO responseTripDTO) {
        try {
            // CSV 파일 경로 설정 (폴더 내)
            File csvFile = new File(filePath);

            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "MS949")))
                  {

                // ResponseTripDTO 객체가 null이 아닌지 확인
                if (responseTripDTO != null) {
                    // ResponseTripDTO 객체를 CSV 문자열로 변환
                    String[] csvData = convertTripDTOToCsv(responseTripDTO);
                    // CSV 파일에 데이터 쓰기
                    bw.write(Arrays.toString(csvData));
                    System.out.println("CSV 파일 저장 완료: " + filePath);
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

    private String[] convertTripDTOToCsv(ResponseTripDTO responseTripDTO) {
        String[] csvData = new String[5]; // 필드 개수에 맞게 배열 크기 조정

        if (responseTripDTO.getId() != null) {
            csvData[0] = responseTripDTO.getId().toString();
        } else {
            // ID가 null인 경우에 대한 처리
            csvData[0] = "";
        }

        csvData[1] = new String(responseTripDTO.getTripName().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        csvData[2] = formatDate(responseTripDTO.getStartDate());
        csvData[3] = formatDate(responseTripDTO.getEndDate());
        if (responseTripDTO.getDirPath() != null) {
            new String(responseTripDTO.getDirPath().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        } else {
            // ID가 null인 경우에 대한 처리
            csvData[4] = "";
        }


        return csvData;
    }

    private String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
    }
