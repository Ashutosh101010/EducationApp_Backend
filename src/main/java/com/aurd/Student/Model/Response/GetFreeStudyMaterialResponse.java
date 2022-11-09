package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.Entity.NotesModel;
import com.aurd.Student.Model.Entity.VideoModel;

import java.util.List;

public class GetFreeStudyMaterialResponse {

    private List<VideoModel> videos;
    private List<NotesEntity> notes;

    String message;
    int errorCode;
    boolean status;


    public List<VideoModel> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoModel> videos) {
        this.videos = videos;
    }

    public List<NotesEntity> getNotes() {
        return notes;
    }

    public void setNotes(List<NotesEntity> notes) {
        this.notes = notes;
    }

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
}
