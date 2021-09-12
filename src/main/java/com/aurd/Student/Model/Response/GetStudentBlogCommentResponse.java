package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.Student_Blog_Commented_Model;

import java.util.ArrayList;

public class GetStudentBlogCommentResponse {

    String message;
    int statusCode;
    boolean status;

    ArrayList<Student_Blog_Commented_Model> comments=new ArrayList();



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


    public ArrayList<Student_Blog_Commented_Model> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Student_Blog_Commented_Model> comments) {
        this.comments = comments;
    }


}




