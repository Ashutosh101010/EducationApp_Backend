package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.QuizQuestionEntity;

import java.util.ArrayList;

public class GetQuizQuestionResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<QuizQuestionEntity> arrayList = new ArrayList();

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

    public ArrayList<QuizQuestionEntity> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<QuizQuestionEntity> arrayList) {
        this.arrayList = arrayList;
    }
}
