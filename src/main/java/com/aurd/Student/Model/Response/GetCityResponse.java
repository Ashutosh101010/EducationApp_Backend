package com.aurd.Student.Model.Response;



import com.aurd.Student.Model.Entity.CityModel;

import java.util.ArrayList;

public class GetCityResponse {

    String message;
    boolean status;
    int statusCode;
     ArrayList<CityModel> cities = new ArrayList();


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

    public ArrayList<CityModel> getCities() {
        return cities;
    }

    public void setCities(ArrayList<CityModel> cities) {
        this.cities = cities;
    }
}



