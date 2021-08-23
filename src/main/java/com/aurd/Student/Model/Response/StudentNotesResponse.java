package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.StudentNotesModel;

import java.util.ArrayList;

public class StudentNotesResponse {
    String message;
    boolean status;
    int statusCode;
    ArrayList<StudentNotesModel> notes = new ArrayList();

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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<StudentNotesModel> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<StudentNotesModel> notes) {
        this.notes = notes;
    }
}
