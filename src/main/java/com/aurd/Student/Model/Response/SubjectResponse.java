package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.SubjectsEntity;
import com.aurd.Student.Model.Entity.StudentNotesModel;

import java.util.ArrayList;

public class SubjectResponse {

    String message;
    boolean status;
    int errorCode;
    ArrayList<SubjectsEntity> subjectsList = new ArrayList();

    ArrayList list = new ArrayList();

    public ArrayList getList() {
        return list;
    }

    public void setList(ArrayList list) {
        this.list = list;
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

    public int geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList<SubjectsEntity> getSubjectsList() {
        return subjectsList;
    }

    public void setSubjectsList(ArrayList<SubjectsEntity> subjectsList) {
        this.subjectsList = subjectsList;
    }
}
