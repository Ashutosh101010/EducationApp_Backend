package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.AudioBook;
import com.aurd.Student.Model.Entity.AudioBookMedia;

import java.util.List;

public class AudioBookMediaResponse {


    private String message;
    private int errorCode;
    private boolean status;
    private List<AudioBookMedia> audioBookMediaList;

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

    public List<AudioBookMedia> getAudioBookMediaList() {
        return audioBookMediaList;
    }

    public void setAudioBookMediaList(List<AudioBookMedia> audioBookMediaList) {
        this.audioBookMediaList = audioBookMediaList;
    }
}
