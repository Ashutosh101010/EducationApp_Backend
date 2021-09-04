package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.BookMarkEntity;
import com.aurd.Student.Model.Entity.BookMarkModel;

import java.util.ArrayList;

public class GetBookMarkResponse {
    String message;
    boolean status;
    int statusCode;

    ArrayList<BookMarkEntity> book = new ArrayList();


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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<BookMarkEntity> getBook() {
        return book;
    }

    public void setBook(ArrayList<BookMarkEntity> book) {
        this.book = book;
    }
}
