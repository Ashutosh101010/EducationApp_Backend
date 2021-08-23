package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.CourseEntity;
import com.aurd.Student.Model.Entity.CourseModel;

import java.util.ArrayList;

public class GetCoursesResponse {
    String message;
    boolean status;
    int statusCode;
    ArrayList<CourseEntity> courses = new ArrayList();

    private int videoLectureCount;
    private  int practiceTestCount;
    private  int notesCount;


    public int getVideoLectureCount() {
        return videoLectureCount;
    }

    public void setVideoLectureCount(int videoLectureCount) {
        this.videoLectureCount = videoLectureCount;
    }

    public int getPracticeTestCount() {
        return practiceTestCount;
    }

    public void setPracticeTestCount(int practiceTestCount) {
        this.practiceTestCount = practiceTestCount;
    }

    public int getNotesCount() {
        return notesCount;
    }

    public void setNotesCount(int notesCount) {
        this.notesCount = notesCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseEntity> courses) {
        this.courses = courses;
    }
}
