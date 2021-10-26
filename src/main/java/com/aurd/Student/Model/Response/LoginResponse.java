package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.StudentModel;

public class LoginResponse {
String message;
boolean status;
int errorCode;
StudentModel student;

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

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
    }
}
