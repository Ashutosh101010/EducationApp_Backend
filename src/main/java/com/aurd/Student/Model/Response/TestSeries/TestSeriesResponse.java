package com.aurd.Student.Model.Response.TestSeries;


import com.aurd.Student.Model.Entity.Quiz;


import java.util.ArrayList;
import java.util.List;

public class TestSeriesResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<Quiz> testSeriesList = new ArrayList<Quiz>();






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

    public ArrayList<Quiz> getTestSeriesList() {
        return testSeriesList;
    }

    public void setTestSeriesList(ArrayList<Quiz> testSeriesList) {
        this.testSeriesList = testSeriesList;
    }
}
