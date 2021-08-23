package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.StudentPostModel;

import java.util.ArrayList;

public class GetStudentPostResponse {
    ArrayList<StudentPostModel> posts = new ArrayList<>();
    String message;
    int errorCode;
    boolean status;

    public ArrayList<StudentPostModel> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<StudentPostModel> posts) {
        this.posts = posts;
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
