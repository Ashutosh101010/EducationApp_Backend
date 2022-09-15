package com.aurd.Student.Model.Response.TestSeries;

import com.aurd.Student.Model.Entity.PractiseTestSeriesModel;
import com.aurd.Student.Model.Entity.QuizModel;

import java.util.ArrayList;
import java.util.List;

public class TestSeriesResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<QuizModel> testSeriesList = new ArrayList<QuizModel>();






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

    public ArrayList<QuizModel> getTestSeriesList() {
        return testSeriesList;
    }

    public void setTestSeriesList(ArrayList<QuizModel> testSeriesList) {
        this.testSeriesList = testSeriesList;
    }
}
