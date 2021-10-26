package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.CommentEntity;

import java.util.ArrayList;

public class GetBlogAndCurrentAffairCommentResponse {


    String message;
    int errorCode;
    boolean status;
    ArrayList<CommentEntity> commentList = new ArrayList<>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(int errorCode) {
        this.errorCode = errorCode;
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
