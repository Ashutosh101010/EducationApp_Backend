package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.*;

import java.util.ArrayList;

public class GetExploreDataResponse {
    ArrayList<BlogModel> blogList = new ArrayList<>();
    ArrayList<CurrentAffairModel> currentAffairArrayList =new ArrayList<>();
    ArrayList<StudentPostEntity> postList = new ArrayList<>();
    ArrayList<NotesModel> notesList = new ArrayList<>();
    ArrayList<QuizModel> quizList = new ArrayList<>();
    String message;
    int errorCode;
    boolean status;

    public ArrayList<QuizModel> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<QuizModel> quizList) {
        this.quizList = quizList;
    }

    public ArrayList<BlogModel> getBlogList() {
        return blogList;
    }

    public void setBlogList(ArrayList<BlogModel> blogList) {
        this.blogList = blogList;
    }

    public ArrayList<CurrentAffairModel> getCurrentAffairArrayList() {
        return currentAffairArrayList;
    }

    public void setCurrentAffairArrayList(ArrayList<CurrentAffairModel> currentAffairArrayList) {
        this.currentAffairArrayList = currentAffairArrayList;
    }

    public ArrayList<StudentPostEntity> getPostList() {
        return postList;
    }

    public void setPostList(ArrayList<StudentPostEntity> postList) {
        this.postList = postList;
    }

    public ArrayList<NotesModel> getNotesList() {
        return notesList;
    }

    public void setNotesList(ArrayList<NotesModel> notesList) {
        this.notesList = notesList;
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
