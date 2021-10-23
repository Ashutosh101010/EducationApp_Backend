package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.LiveClassModel;

import java.util.ArrayList;

public class GetLiveClassResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<LiveClassModel> list = new ArrayList<>();

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

    public ArrayList<LiveClassModel> getList() {
        return list;
    }

    public void setList(ArrayList<LiveClassModel> list) {
        this.list = list;
    }
}
