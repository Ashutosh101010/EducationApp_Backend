package com.aurd.Student.Model.Response.TestSeries;

import com.aurd.Student.Model.Entity.TestSeries;


import java.util.ArrayList;

public class PractiseTestSeries_Response {
    String message;
    int errorCode;
    boolean status;
    ArrayList<TestSeries> practiseTestList = new ArrayList();

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

    public ArrayList<TestSeries> getPractiseTestList() {
        return practiseTestList;
    }

    public void setPractiseTestList(ArrayList<TestSeries> practiseTestList) {
        this.practiseTestList = practiseTestList;
    }
}

