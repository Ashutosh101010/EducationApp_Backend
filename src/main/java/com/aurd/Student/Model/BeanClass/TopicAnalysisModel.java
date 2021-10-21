package com.aurd.Student.Model.BeanClass;

public class TopicAnalysisModel {
    String subject;
    int questions;
    double percent;
    int marksObtained;
    int totalMarks;
    int cutOff;
    String status;
    int correctAns;
    int wrongAns;
    int skipped;

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public int getWrongAns() {
        return wrongAns;
    }

    public void setWrongAns(int wrongAns) {
        this.wrongAns = wrongAns;
    }

    public int getSkipped() {
        return skipped;
    }

    public void setSkipped(int skipped) {
        this.skipped = skipped;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCutOff() {
        return cutOff;
    }

    public void setCutOff(int cutOff) {
        this.cutOff = cutOff;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getQuestions() {
        return questions;
    }

    public void setQuestions(int questions) {
        this.questions = questions;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(int marksObtained) {
        this.marksObtained = marksObtained;
    }
}
