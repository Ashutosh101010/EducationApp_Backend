package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.BlogModel;

import java.util.ArrayList;

public class GetBlogResponse {

    String message;
    int status;
    boolean statusCode;
    ArrayList<BlogModel> blogs = new ArrayList();


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isStatusCode() {
        return statusCode;
    }

    public void setStatusCode(boolean statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<BlogModel> getBlogs() {
        return blogs;
    }

    public void setBlogs(ArrayList<BlogModel> blogs) {
        this.blogs = blogs;
    }
}
