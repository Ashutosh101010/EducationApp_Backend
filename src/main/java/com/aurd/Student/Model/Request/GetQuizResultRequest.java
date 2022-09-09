package com.aurd.Student.Model.Request;

public class GetQuizResultRequest {
    Long instID;
    Long studID;
    Long quizID;
    String testName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Long getInstID() {
        return instID;
    }

    public void setInstID(Long instID) {
        this.instID = instID;
    }

    public Long getStudID() {
        return studID;
    }

    public void setStudID(Long studID) {
        this.studID = studID;
    }

    public Long getQuizID() {
        return quizID;
    }

    public void setQuizID(Long quizID) {
        this.quizID = quizID;
    }
}
