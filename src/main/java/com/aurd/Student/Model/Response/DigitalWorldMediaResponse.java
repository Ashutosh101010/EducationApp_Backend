package com.aurd.Student.Model.Response;


import com.aurd.Student.Model.Entity.DigitalWorldMedia;

import java.util.List;

public class DigitalWorldMediaResponse {
    private String message;
    private int errorCode;
    private boolean status;
    private List<DigitalWorldMedia> mediaList;

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

    public List<DigitalWorldMedia> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<DigitalWorldMedia> mediaList) {
        this.mediaList = mediaList;
    }
}
