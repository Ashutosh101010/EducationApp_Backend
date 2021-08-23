package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.QuizModel;

import java.util.ArrayList;

public class GetQuizResponse {
    String message;
    int errorCode;
    boolean status;
    ArrayList<QuizModel> quizList = new ArrayList<>();

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

    public ArrayList<QuizModel> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<QuizModel> quizList) {
        this.quizList = quizList;
    }
}
