package com.aurd.Student.Model.Response.TestSeries;

import com.aurd.Student.Model.Entity.Quiz;
import java.util.ArrayList;

public class MonthlytestSeriesResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<Quiz> testList = new ArrayList<>();

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

    public ArrayList<Quiz> getTestList() {
        return testList;
    }

    public void setTestList(ArrayList<Quiz> testList) {
        this.testList = testList;
    }
}
