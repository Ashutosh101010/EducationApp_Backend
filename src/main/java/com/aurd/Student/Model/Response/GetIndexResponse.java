package com.aurd.Student.Model.Response;


import com.aurd.Student.Model.Entity.Index_Model;

import java.util.ArrayList;
import java.util.List;

public class GetIndexResponse {


    String message;
    int errorCode;
    boolean status;
 List<Index_Model> index = new ArrayList();
    ArrayList<Object> objList = new ArrayList<>();

    public ArrayList<Object> getObjList() {
        return objList;
    }

    public void setObjList(ArrayList<Object> objList) {
        this.objList = objList;
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

    public List<Index_Model> getIndex() {
        return index;
    }

    public void setIndex(List<Index_Model> index) {
        this.index = index;
    }
}

