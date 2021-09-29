package com.aurd.Student.Model.BeanClass;

public class ResultEntity {
    private  long marksObtained;
    private  long totalMarks;
    private Long cutOff;
    private long correctAnswered;
    private  long wrongAnswered;

    public long getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(long marksObtained) {
        this.marksObtained = marksObtained;
    }

    public long getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(long totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Long getCutOff() {
        return cutOff;
    }

    public void setCutOff(Long cutOff) {
        this.cutOff = cutOff;
    }

    public long getCorrectAnswered() {
        return correctAnswered;
    }

    public void setCorrectAnswered(long correctAnswered) {
        this.correctAnswered = correctAnswered;
    }

    public long getWrongAnswered() {
        return wrongAnswered;
    }

    public void setWrongAnswered(long wrongAnswered) {
        this.wrongAnswered = wrongAnswered;
    }


}
