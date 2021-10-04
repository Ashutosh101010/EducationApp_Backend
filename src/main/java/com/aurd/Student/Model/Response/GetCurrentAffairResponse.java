package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.Entity.CurrentAffairModel;

import java.util.ArrayList;

public class GetCurrentAffairResponse {
    String message;
    boolean status;
    int statusCode;
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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }


    public ArrayList<CurrentAffairEntity> getCurrentAffair() {
        return currentAffair;
    }

    public void setCurrentAffair(ArrayList<CurrentAffairEntity> currentAffair) {
        this.currentAffair = currentAffair;
    }
}
