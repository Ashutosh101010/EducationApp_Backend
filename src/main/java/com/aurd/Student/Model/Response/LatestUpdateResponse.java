package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.NotesEntity;
import com.aurd.Student.Model.Entity.BlogModel;
import com.aurd.Student.Model.Entity.CurrentAffairModel;

import java.util.ArrayList;

public class LatestUpdateResponse {
    String message;
    int errorCode;
    boolean status;

    ArrayList<BlogModel> blogList = new ArrayList<>();
    ArrayList<CurrentAffairModel> currentAffairList = new ArrayList<>();
    ArrayList<NotesEntity> notesList = new ArrayList<>();

    public ArrayList<NotesEntity> getNotesList() {
        return notesList;
    }

    public void setNotesList(ArrayList<NotesEntity> notesList) {
        this.notesList = notesList;
    }

    public ArrayList<CurrentAffairModel> getCurrentAffairList() {
        return currentAffairList;
    }

    public void setCurrentAffairList(ArrayList<CurrentAffairModel> currentAffairList) {
        this.currentAffairList = currentAffairList;
    }

    public ArrayList<BlogModel> getBlogList() {
        return blogList;
    }

    public void setBlogList(ArrayList<BlogModel> blogList) {
        this.blogList = blogList;
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
