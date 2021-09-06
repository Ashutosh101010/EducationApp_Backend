package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.StudentCommentPostEntity;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;

import java.util.ArrayList;

public class StudentPostCommentResponse {
    String message;
    int statusCode;
    boolean status;

    ArrayList<StudentCommentPostEntity>comments=new ArrayList();



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

    public ArrayList<StudentCommentPostEntity> getComments() {
        return comments;
    }

    public void setComments(ArrayList<StudentCommentPostEntity> comments) {
        this.comments = comments;
    }




}
