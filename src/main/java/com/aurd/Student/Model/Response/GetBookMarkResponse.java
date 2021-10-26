package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.BookMarkEntity;
import com.aurd.Student.Model.BeanClass.CurrentAffairEntity;
import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.BookMarkModel;
import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Entity.StudentPostModel;

import java.util.ArrayList;

public class GetBookMarkResponse {
    String message;
    boolean status;
    int errorCode;

//    ArrayList<BookMarkEntity> book = new ArrayList();

    ArrayList<StudentPostEntity> postList = new ArrayList<>();
    ArrayList<CurrentAffairEntity> caList = new ArrayList<>();
    ArrayList<StudentPostEntity> pList = new ArrayList<>();

    public ArrayList<StudentPostEntity> getpList() {
        return pList;
    }

    public void setpList(ArrayList<StudentPostEntity> pList) {
        this.pList = pList;
    }

    public ArrayList<CurrentAffairEntity> getCaList() {
        return caList;
    }

    public void setCaList(ArrayList<CurrentAffairEntity> caList) {
        this.caList = caList;
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


}
