package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.Video;

import java.util.ArrayList;

public class GetVideoLectureResponse {

    ArrayList<Video> videoList = new ArrayList<>();
    int errorCode;
    boolean status;
    String message;

    public ArrayList<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(ArrayList<Video> videoList) {
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
