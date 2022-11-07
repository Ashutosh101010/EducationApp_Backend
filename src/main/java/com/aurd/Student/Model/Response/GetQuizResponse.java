package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.QuizEntity;
import com.aurd.Student.Model.Entity.Quiz;

import java.util.ArrayList;

public class GetQuizResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<Quiz> quizList;
    ArrayList<QuizEntity>  arrayList = new ArrayList<>();

    public ArrayList<QuizEntity> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<QuizEntity> arrayList) {
        this.arrayList = arrayList;
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

    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }
}
