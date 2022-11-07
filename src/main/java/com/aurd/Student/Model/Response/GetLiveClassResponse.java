package com.aurd.Student.Model.Response;


import com.aurd.Student.Model.Entity.LiveClass;

import java.util.ArrayList;

public class GetLiveClassResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<LiveClass> list = new ArrayList<>();

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

    public ArrayList<LiveClass> getList() {
        return list;
    }

    public void setList(ArrayList<LiveClass> list) {
        this.list = list;
    }
}
