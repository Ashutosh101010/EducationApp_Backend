package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.Audio;
import com.aurd.Student.Model.Entity.VideoModel;

import java.util.ArrayList;

public class GetAudioNotesResponse {

    ArrayList<Audio> videoList = new ArrayList<>();
    int errorCode;
    boolean status;
    String message;

    public ArrayList<Audio> getVideoList() {
        return videoList;
    }

    public void setAudioList(ArrayList<Audio> videoList) {
        this.videoList = videoList;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
