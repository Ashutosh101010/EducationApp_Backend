package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.FunBox;

import java.util.ArrayList;
import java.util.List;

public class FunBoxResponse {

    private String message;
    private int errorCode;
    private boolean status;
    private List<FunBox> funBoxList;

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

    public List<FunBox> getFunBoxList() {
        return funBoxList;
    }

    public void setFunBoxList(List<FunBox> funBoxList) {
        this.funBoxList = funBoxList;
    }
}
