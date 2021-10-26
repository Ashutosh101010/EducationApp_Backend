package com.aurd.Student.Model.Response;

public class GeneralResponse {
    String message;
    int errorCode;
    boolean status;


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
}
