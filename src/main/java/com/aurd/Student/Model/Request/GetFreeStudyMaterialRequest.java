package com.aurd.Student.Model.Request;

import javax.ws.rs.Path;


public class GetFreeStudyMaterialRequest {

    private Integer instId;
    private Integer courseId;
    private Integer studId;

    public Integer getInstId() {
        return instId;
    }

    public void setInstId(Integer instId) {
        this.instId = instId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudId() {
        return studId;
    }

    public void setStudId(Integer studId) {
        this.studId = studId;
    }
}
