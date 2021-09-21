package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.CityModel;
import com.aurd.Student.Model.Entity.StateModel;

import java.util.ArrayList;

public class MetaDataResponse {
    String message;
    boolean status;
    int errorCode;
    ArrayList<StateModel> stateList = new ArrayList<>();
    ArrayList<CityModel> cityList = new ArrayList<>();



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

    public ArrayList<StateModel> getStateList() {
        return stateList;
    }

    public void setStateList(ArrayList<StateModel> stateList) {
        this.stateList = stateList;
    }

    public ArrayList<CityModel> getCityList() {
        return cityList;
    }

    public void setCityList(ArrayList<CityModel> cityList) {
        this.cityList = cityList;
    }
}
