package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.AudioBook;

import java.util.List;

public class AudioBookResponse {

    private String message;
    private int errorCode;
    private boolean status;
    private List<AudioBook> audioBookList;

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

    public List<AudioBook> getAudioBookList() {
        return audioBookList;
    }

    public void setAudioBookList(List<AudioBook> audioBookList) {
        this.audioBookList = audioBookList;
    }
}
