package com.aurd.Student.Model.Response.TestSeries;

import com.aurd.Student.Model.BeanClass.ResultEntity;

import java.util.ArrayList;

public class Result_Response {
    String message;
    int errorCode;
    boolean status;
    ResultEntity resultEntity;

    public ResultEntity getResultEntity() {
        return resultEntity;
    }

    public void setResultEntity(ResultEntity resultEntity) {
        this.resultEntity = resultEntity;
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
