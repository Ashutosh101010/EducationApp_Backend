package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.GetInstituteDetailEntity;

import java.util.ArrayList;

public class GetInstituteDetailsResponse {

    String message;
    int errorCode;
    boolean status;
    ArrayList<GetInstituteDetailEntity> details = new ArrayList();


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

    public ArrayList<GetInstituteDetailEntity> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<GetInstituteDetailEntity> details) {
        this.details = details;
    }



}
