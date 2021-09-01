package com.aurd.Student.Model.Response.TestSeries;

import com.aurd.Student.Model.Entity.QuizModel;

import java.util.ArrayList;

public class MonthlytestSeriesResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<QuizModel> testList = new ArrayList<>();

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

    public ArrayList<QuizModel> getTestList() {
        return testList;
    }

    public void setTestList(ArrayList<QuizModel> testList) {
        this.testList = testList;
    }
}
