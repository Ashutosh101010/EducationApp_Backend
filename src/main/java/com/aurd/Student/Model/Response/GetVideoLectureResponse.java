package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.VideoModel;

import java.util.ArrayList;

public class GetVideoLectureResponse {

    ArrayList<VideoModel> videoList = new ArrayList<>();
    int errorCode;
    boolean status;
    String message;

    public ArrayList<VideoModel> getVideoList() {
        return videoList;
    }

    public void setVideoList(ArrayList<VideoModel> videoList) {
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
