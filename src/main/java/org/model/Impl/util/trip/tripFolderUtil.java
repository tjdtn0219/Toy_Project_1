package org.model.Impl.util.trip;

import java.io.File;

public class tripFolderUtil {

    public int folderFindHighestNumber(String path, String baseFileName) {
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


    public void createFolderIfNotExists(String path, String folderName) {
        File folder = new File(path + File.separator + folderName);
        if (!folder.exists()) {
            folder.mkdir();
        } else {
            System.out.println("이미 폴더가 생성되어 있습니다: " + folder.getPath());
        }
    }
}
