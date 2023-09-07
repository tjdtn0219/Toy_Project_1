package org.model.Impl;


import org.dto.ResponseTripDTO;
import org.model.Impl.util.trip.tripCsvUtil;
import org.model.Impl.util.trip.tripFolderUtil;
import org.model.Impl.util.trip.tripJsonUtil;
import org.model.TripModel;

import java.io.*;
import java.util.*;


public class TripModelImpl implements TripModel {
    String baseFileName = "MyTrip_"; // Unique identifier
    String jsonfileExtension = ".json"; // json파일 확장자
    String csvfileExtension = ".csv"; // csv파일 확장자
    String jsonPath = "C:\\Users\\82102\\Desktop\\JSON"; //폴더 경로
    String csvPath = "C:\\Users\\82102\\Desktop\\CSV"; //폴더 경로

    public ResponseTripDTO save(ResponseTripDTO responseTripDTO) {
        try {
            tripFolderUtil tripFolderUtil = new tripFolderUtil();
            tripJsonUtil tripJsonUtil = new tripJsonUtil();
            tripCsvUtil tripCsvUtil = new tripCsvUtil();

            int highestJsonNumber = tripFolderUtil.folderFindHighestNumber(jsonPath, baseFileName);
            int highestCsvNumber = tripFolderUtil.folderFindHighestNumber(csvPath, baseFileName);

            responseTripDTO.setId(highestJsonNumber+1);

            // 폴더명 생성
            String jsonFolderName = baseFileName + (highestJsonNumber + 1);
            String csvFolderName = baseFileName + (highestCsvNumber + 1);

            // JSON 폴더 생성
            tripFolderUtil.createFolderIfNotExists(jsonPath, jsonFolderName);


            String jsonFilePath = jsonPath + File.separator + jsonFolderName + File.separator + jsonFolderName + jsonfileExtension;
            String csvFilePath = csvPath + File.separator + csvFolderName + File.separator + csvFolderName + csvfileExtension;

            // CSV 폴더 생성
            tripFolderUtil.createFolderIfNotExists(csvPath, csvFolderName);

            // JSON 파일 생성
            tripJsonUtil.saveDTOAsJson(jsonFilePath, responseTripDTO);

            tripCsvUtil.saveDTOAsCsv(csvFilePath, responseTripDTO);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseTripDTO;
    }

    @Override
    public List<ResponseTripDTO> JSONfindAll() {
        tripJsonUtil tripJsonUtil = new tripJsonUtil();
        List<ResponseTripDTO> tripList = tripJsonUtil.findAllJsonFiles(jsonPath);
        return tripList;
    }

    @Override
    public List<ResponseTripDTO> CSVfindAll() {
        tripCsvUtil tripCsvUtil = new tripCsvUtil();
        List<ResponseTripDTO> tripList = tripCsvUtil.findAllCsvFiles(csvPath);
        System.out.println(tripList.toString());
        return tripList;
    }
    }
