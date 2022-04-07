package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.NotesEntity;

import java.util.ArrayList;

public class GetNotesResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<NotesEntity> notes = new ArrayList();


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

    public ArrayList<NotesEntity> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<NotesEntity> notes) {
        this.notes = notes;
    }


    @Override
    public String toString() {
        return "GetNotesResponse{" +
                "message='" + message + '\'' +
                ", errorCode=" + errorCode +
                ", status=" + status +
                ", notes=" + notes +
                '}';
    }
}
