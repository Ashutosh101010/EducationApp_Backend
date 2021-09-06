package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.StudentModel;

import java.util.ArrayList;

public class GetProfileResponse {

    String message;
    int statusCode;
    boolean status;

    StudentModel studentModel=new StudentModel();

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setProfile(StudentModel studentModel) {
        this.studentModel=studentModel;
    }



}

