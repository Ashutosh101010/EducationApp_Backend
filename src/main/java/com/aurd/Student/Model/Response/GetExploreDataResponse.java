package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.BlogEntity;
import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.*;

import java.util.ArrayList;

public class GetExploreDataResponse {
    ArrayList<BlogEntity> blogList = new ArrayList<>();
    ArrayList<CurrentAffairEntity> currentAffairArrayList =new ArrayList<>();
    ArrayList<StudentPostEntity> postList = new ArrayList<>();
    ArrayList<NotesEntity> notesList = new ArrayList<>();
    ArrayList<Quiz> quizList = new ArrayList<>();
    ArrayList<Video> videoModels=new ArrayList<>();
    ArrayList<Quiz>practiseList = new ArrayList<>();
    String message;
    int errorCode;
    boolean status;


    public ArrayList<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<Quiz> quizList) {
        this.quizList = quizList;
    }

    public ArrayList<Video> getVideoModels() {
        return videoModels;
    }

    public void setVideoModels(ArrayList<Video> videoModels) {
        this.videoModels = videoModels;
    }

    public ArrayList<Quiz> getPractiseList() {
        return practiseList;
    }

    public void setPractiseList(ArrayList<Quiz> practiseList) {
        this.practiseList = practiseList;
    }

    public ArrayList<NotesEntity> getNotesList() {
        return notesList;
    }

    public void setNotesList(ArrayList<NotesEntity> notesList) {
        this.notesList = notesList;
    }


    public ArrayList<BlogEntity> getBlogList() {
        return blogList;
    }

    public void setBlogList(ArrayList<BlogEntity> blogList) {
        this.blogList = blogList;
    }

    public ArrayList<CurrentAffairEntity> getCurrentAffairArrayList() {
        return currentAffairArrayList;
    }

    public void setCurrentAffairArrayList(ArrayList<CurrentAffairEntity> currentAffairArrayList) {
        this.currentAffairArrayList = currentAffairArrayList;
    }

    public ArrayList<StudentPostEntity> getPostList() {
        return postList;
    }

    public void setPostList(ArrayList<StudentPostEntity> postList) {
        this.postList = postList;
    }



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



}
