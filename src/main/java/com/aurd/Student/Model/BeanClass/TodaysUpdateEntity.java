package com.aurd.Student.Model.BeanClass;

import com.aurd.Student.Model.Entity.CourseModel;
import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.VideoModel;

import java.util.ArrayList;

public class TodaysUpdateEntity {
    ArrayList<CourseModel> courses = new ArrayList<>();
    ArrayList<VideoModel> videos = new ArrayList<>();
    ArrayList<QuizModel> quizzes = new ArrayList<>();
    String message;
    boolean status;
    int errorCode;

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

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList<CourseModel> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<CourseModel> courses) {
        this.courses = courses;
    }

    public ArrayList<VideoModel> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<VideoModel> videos) {
        this.videos = videos;
    }

    public ArrayList<QuizModel> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<QuizModel> quizzes) {
        this.quizzes = quizzes;
    }
}
