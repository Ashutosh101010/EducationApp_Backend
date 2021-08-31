package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.StateModel;

import java.util.ArrayList;

public class GetStateResponse {
    String message;
    boolean status;
    int statusCode;



    ArrayList<String> states = new ArrayList();

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
    public int getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public ArrayList<String> getStates() {
        return states;
    }

    public void setStates(ArrayList<String> states) {
        this.states = states;
    }
}
