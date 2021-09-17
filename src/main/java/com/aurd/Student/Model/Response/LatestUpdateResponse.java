package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.BlogEntity;
import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Entity.NotesModel;
import com.aurd.Student.Model.Entity.QuizModel;

import java.util.ArrayList;

public class LatestUpdateResponse {
    ArrayList<BlogEntity> blogList = new ArrayList<>();
    ArrayList<CurrentAffairEntity> currentAffairArrayList =new ArrayList<>();
    ArrayList<StudentPostEntity> postList = new ArrayList<>();
    ArrayList<NotesModel> notesList = new ArrayList<>();
    ArrayList<QuizModel> quizList = new ArrayList<>();
    String message;
    int errorCode;
    boolean status;

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

    public ArrayList<NotesModel> getNotesList() {
        return notesList;
    }

    public void setNotesList(ArrayList<NotesModel> notesList) {
        this.notesList = notesList;
    }

    public ArrayList<QuizModel> getQuizList() {
        return quizList;
    }

    public void setQuizList(ArrayList<QuizModel> quizList) {
        this.quizList = quizList;
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
