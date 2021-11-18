package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.CourseEntity;

public class GetCourseDetailResponse {

    String message;
    int errorCode;
    boolean status;
    CourseEntity entity;

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

    public CourseEntity getEntity() {
        return entity;
    }

    public void setEntity(CourseEntity entity) {
        this.entity = entity;
    }
}
