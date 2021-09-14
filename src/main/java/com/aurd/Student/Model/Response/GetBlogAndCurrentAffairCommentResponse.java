package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.CommentEntity;

import java.util.ArrayList;

public class GetBlogAndCurrentAffairCommentResponse {


    String message;
    int statusCode;
    boolean status;
    ArrayList<CommentEntity> commentList = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<CommentEntity> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<CommentEntity> commentList) {
        this.commentList = commentList;
    }
}
