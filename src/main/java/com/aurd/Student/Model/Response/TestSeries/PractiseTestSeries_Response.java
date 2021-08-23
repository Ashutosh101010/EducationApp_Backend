package com.aurd.Student.Model.Response.TestSeries;

import com.aurd.Student.Model.Entity.TestSeriesModel;

import java.util.ArrayList;

public class PractiseTestSeries_Response {
    String message;
    int errorCode;
    boolean status;
    ArrayList<TestSeriesModel> practiseTestList = new ArrayList();

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

    public ArrayList<TestSeriesModel> getPractiseTestList() {
        return practiseTestList;
    }

    public void setPractiseTestList(ArrayList<TestSeriesModel> practiseTestList) {
        this.practiseTestList = practiseTestList;
    }
}
