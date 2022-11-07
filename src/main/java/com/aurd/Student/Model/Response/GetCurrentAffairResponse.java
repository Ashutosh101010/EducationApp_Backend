package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;


import java.util.ArrayList;

public class GetCurrentAffairResponse {
    String message;
    boolean status;
    int errorCode;
    ArrayList<CurrentAffairEntity> currentAffair = new ArrayList();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(int errorCode) {
        this.errorCode = errorCode;
    }


    public ArrayList<CurrentAffairEntity> getCurrentAffair() {
        return currentAffair;
    }

    public void setCurrentAffair(ArrayList<CurrentAffairEntity> currentAffair) {
        this.currentAffair = currentAffair;
    }
}
