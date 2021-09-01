package com.aurd.Student.Model.Response.TestSeries;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.TestSeriesModel;

import java.util.ArrayList;

public class TestSeriesResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<TestSeriesModel> testSeriesList = new ArrayList<>();

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

    public ArrayList<TestSeriesModel> getTestSeriesList() {
        return testSeriesList;
    }

    public void setTestSeriesList(ArrayList<TestSeriesModel> testSeriesList) {
        this.testSeriesList = testSeriesList;
    }
}
