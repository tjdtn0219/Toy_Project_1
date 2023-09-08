package org.travelrecord.model.Impl.util;

import org.travelrecord.exception.FolderErrorCode;
import org.travelrecord.exception.FolderException;
import org.travelrecord.model.Impl.TripModelImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties에서 별도 관리되어지고 있는 json, csv 저장 파일 경로를 가져와 getter로 제공하는 클래스.
 * 생성자로 필요로직을 바로하기에 객체 생성 후 바로 getter로 접근하여 파일 경로를 받아낼 수 있다.
 */
public class LoadPath {

    private String jsonPath, csvPath;
    
    // properties 이름
    private final String propertiesPath = "data.properties";
    // properties 에 저장된 json 폴더 위치
    private final String propertiesJsonPath = "path.jsonPath";
    // properties 에 저장된 csv 폴더 위치
    private final String propertiesCsvPath = "path.csvPath";


    public LoadPath() {
        loadPath();
    }

    public void loadPath() {
        ClassLoader classLoader = TripModelImpl.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(propertiesPath);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            jsonPath = properties.getProperty(propertiesJsonPath);
            csvPath = properties.getProperty(propertiesCsvPath);

            // <<데이터 저장 경로에 대한 예외처리>>
            // 경로값을 받아오지 못하는 경우
            if(jsonPath == null) {
                throw new FolderException(FolderErrorCode.NO_JSON_FOLDER_PATH_VALUE);
            }
            if(csvPath == null) {
                throw new FolderException(FolderErrorCode.NO_CSV_FOLDER_PATH_VALUE);
            }
            // 지정된 경로에 파일이 없을 경우(경로가 틀린경우, 비어있는 경우)
            if(!new File(jsonPath).exists() || jsonPath.isEmpty()) {
                throw new FolderException(FolderErrorCode.NO_JSON_FOLDER);
            }
            if(!new File(csvPath).exists() || csvPath.isEmpty()) {
                throw new FolderException(FolderErrorCode.NO_CSV_FOLDER);
            }
        } catch (FolderException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public String getCsvPath() {
        return csvPath;
    }
}
