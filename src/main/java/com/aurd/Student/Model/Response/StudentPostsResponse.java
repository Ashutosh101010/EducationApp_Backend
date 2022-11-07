package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.StudentPosts;


import java.util.ArrayList;

public class StudentPostsResponse {


    int errorCode;
    boolean status;
    String message;

    public ArrayList<StudentPosts> getStudentPosts() {
        return studentPosts;
    }

    public void setStudentPosts(ArrayList<StudentPosts> studentPosts) {
        this.studentPosts = studentPosts;
    }

    ArrayList<StudentPosts> studentPosts=new ArrayList<>()  ;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
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


}
