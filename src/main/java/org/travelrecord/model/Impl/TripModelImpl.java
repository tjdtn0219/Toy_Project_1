package org.travelrecord.model.Impl;


import org.travelrecord.Entity.TripEntity;
import org.travelrecord.model.Impl.util.LoadPath;
import org.travelrecord.model.Impl.util.trip.tripCsvUtil;
import org.travelrecord.model.Impl.util.trip.tripFolderUtil;
import org.travelrecord.model.Impl.util.trip.tripJsonUtil;
import org.travelrecord.model.TripModel;

import java.io.*;
import java.util.*;


public class TripModelImpl implements TripModel {
    String baseFileName = "MyTrip_"; // Unique identifier
    String jsonfileExtension = ".json"; // json파일 확장자
    String csvfileExtension = ".csv"; // csv파일 확장자

    String jsonPath, csvPath;

    public TripModelImpl() {
        LoadPath loadPath = new LoadPath();
        // 파일 경로 값 properties 에서 가져와 적용
        jsonPath = loadPath.getJsonPath();
        csvPath = loadPath.getCsvPath();
    }

    public TripEntity save(TripEntity tripEntity) {
        try {
            tripFolderUtil tripFolderUtil = new tripFolderUtil();
            tripJsonUtil tripJsonUtil = new tripJsonUtil();
            tripCsvUtil tripCsvUtil = new tripCsvUtil();

            int highestJsonNumber = tripFolderUtil.folderFindHighestNumber(jsonPath, baseFileName);
            int highestCsvNumber = tripFolderUtil.folderFindHighestNumber(csvPath, baseFileName);

            tripEntity.setId(highestJsonNumber+1);

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
            tripJsonUtil.saveDTOAsJson(jsonFilePath, tripEntity);

            tripCsvUtil.saveDTOAsCsv(csvFilePath, tripEntity);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return tripEntity;
    }

    @Override
    public List<TripEntity> JSONfindAll() {
        tripJsonUtil tripJsonUtil = new tripJsonUtil();
        List<TripEntity> tripList = tripJsonUtil.findAllJsonFiles(jsonPath);
        return tripList;
    }

    @Override
    public List<TripEntity> CSVfindAll() {
        tripCsvUtil tripCsvUtil = new tripCsvUtil();
        List<TripEntity> tripList = tripCsvUtil.findAllCsvFiles(csvPath);
        return tripList;
    }
    }
