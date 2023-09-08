package org.model.Impl.util;

import org.model.Impl.TripModelImpl;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public String getCsvPath() {
        return csvPath;
    }
}
