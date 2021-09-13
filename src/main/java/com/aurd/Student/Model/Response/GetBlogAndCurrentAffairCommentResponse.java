package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.Current_AffairsCommented_Model;
import com.aurd.Student.Model.Entity.Student_Blog_Commented_Model;

import java.util.ArrayList;

public class GetBlogAndCurrentAffairCommentResponse {


    String message;
    int statusCode;
    boolean status;
    ArrayList<Student_Blog_Commented_Model> blogCommentList=new ArrayList();
    ArrayList<Current_AffairsCommented_Model> caList =new ArrayList();


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

    public ArrayList<Student_Blog_Commented_Model> getBlogCommentList() {
        return blogCommentList;
    }

    public void setBlogCommentList(ArrayList<Student_Blog_Commented_Model> blogCommentList) {
        this.blogCommentList = blogCommentList;
    }

    public ArrayList<Current_AffairsCommented_Model> getCaList() {
        return caList;
    }

    public void setCaList(ArrayList<Current_AffairsCommented_Model> caList) {
        this.caList = caList;
    }
}
