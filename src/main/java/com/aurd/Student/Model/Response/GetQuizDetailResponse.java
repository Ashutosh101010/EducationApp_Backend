package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.QuizModel;

public class GetQuizDetailResponse {

    QuizModel quizModel;
    String message;
    int errorCode;
    boolean status;

    public QuizModel getQuizModel() {
        return quizModel;
    }

    public void setQuizModel(QuizModel quizModel) {
        this.quizModel = quizModel;
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
