package org.travelrecord.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FolderErrorCode {

    NO_JSON_FOLDER("\n[ERROR] 지정된 폴더위치에 Json 폴더가 없습니다.\n"),
    NO_CSV_FOLDER("\n[ERROR] 지정된 폴더위치에 Csv 폴더가 없습니다.\n"),
    NO_CSV_FOLDER_PATH_VALUE("\n[ERROR] Json 폴더위치를 불어오지 못하고 있습니다.\n"),
    NO_JSON_FOLDER_PATH_VALUE("\n[ERROR] Csv 폴더위치를 불어오지 못하고 있습니다.\n");

    private final String message;

}
