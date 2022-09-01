package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.BooksMedia;

import java.util.List;

public class GetPhysicalBookMediaResponse {

    private String message;
    private int errorCode;
    private boolean status;
    private List<BooksMedia> mediaList;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<BooksMedia> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<BooksMedia> mediaList) {
        this.mediaList = mediaList;
    }
}
