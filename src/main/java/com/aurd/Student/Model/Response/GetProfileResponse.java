package com.aurd.Student.Model.Response;


import com.aurd.Student.Model.Entity.Student;

import java.util.ArrayList;

public class GetProfileResponse {

    String message;
    int errorCode;
    boolean status;

    Student studentModel;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Student getStudentModel() {
        return studentModel;
    }

    public void setStudentModel(Student studentModel) {
        this.studentModel = studentModel;
    }
}

