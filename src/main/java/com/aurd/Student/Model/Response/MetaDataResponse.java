package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.City;
import com.aurd.Student.Model.Entity.State;


import java.util.ArrayList;

public class MetaDataResponse {
    String message;
    boolean status;
    int errorCode;
    ArrayList<State> stateList = new ArrayList<>();
    ArrayList<City> cityList = new ArrayList<>();



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

   public ArrayList<String> getStates() {
        return states;
    }

    public void setStates(ArrayList<String> states) {
        this.states = states;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList<State> getStateList() {
        return stateList;
    }

    public void setStateList(ArrayList<State> stateList) {
        this.stateList = stateList;
    }

    public ArrayList<City> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<City> cityList) {
        this.cityList = cityList;
    }
}
