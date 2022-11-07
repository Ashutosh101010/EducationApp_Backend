package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.Quiz;


import java.util.ArrayList;

public class GetCourse_PractiseTest_Response
{
    String message;
    int errorCode;
    boolean status;
    ArrayList<Quiz> practiseTestList  = new ArrayList<>();

    public ArrayList<Quiz> getPractiseTestList() {
        return practiseTestList;
    }

    public void setPractiseTestList(ArrayList<Quiz> practiseTestList) {
        this.practiseTestList = practiseTestList;
    }

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
}
