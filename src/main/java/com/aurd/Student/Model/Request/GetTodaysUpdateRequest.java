package com.aurd.Student.Model.Request;

public class GetTodaysUpdateRequest {

    long studId;
    long instID;

    public long getStudId() {
        return studId;
    }

    public void setStudId(long studId) {
        this.studId = studId;
    }

    public long getInstID() {
        return instID;
    }

    public void setInstID(long instID) {
        this.instID = instID;
    }
}
