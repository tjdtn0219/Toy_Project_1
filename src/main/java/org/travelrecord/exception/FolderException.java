package org.travelrecord.exception;

public class FolderException extends Exception{

    private FolderErrorCode folderErrorCode;

    public FolderException(FolderErrorCode errorCode) {
        super(errorCode.getMessage());
        this.folderErrorCode = errorCode;
    }

}
