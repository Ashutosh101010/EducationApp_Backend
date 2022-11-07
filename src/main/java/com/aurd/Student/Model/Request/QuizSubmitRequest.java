package com.aurd.Student.Model.Request;

import com.aurd.Student.Model.Entity.QuizSubmit;

import java.util.ArrayList;

public class QuizSubmitRequest {
    ArrayList<QuizSubmit> arrayList = new ArrayList();
//    ArrayList arrayList = new ArrayList();
    String time;
    Long quizId; //series id in practisetestseries
    Long studentId;
    Long instId;
    String testName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getInstId() {
        return instId;
    }

    public void setInstId(Long instId) {
        this.instId = instId;
    }

    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(Long quizId) {
        this.quizId = quizId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<QuizSubmit> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<QuizSubmit> arrayList) {
        this.arrayList = arrayList;
    }
}
