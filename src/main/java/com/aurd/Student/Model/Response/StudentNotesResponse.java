package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.StudentNotesEntity;

import java.util.ArrayList;

public class StudentNotesResponse {
    String message;
    boolean status;
    int errorCode;
    ArrayList<StudentNotesEntity> notes = new ArrayList();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList<StudentNotesEntity> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<StudentNotesEntity> notes) {
        this.notes = notes;
    }
}
