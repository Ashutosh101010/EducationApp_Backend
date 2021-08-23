package com.aurd.Student.Model.Request;

public class GetStudentCourseRequest {
    long userId;
    long inst_id;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getInst_id() {
        return inst_id;
    }

    public void setInst_id(long inst_id) {
        this.inst_id = inst_id;
    }
}
