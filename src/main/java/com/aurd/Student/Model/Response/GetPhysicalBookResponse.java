package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.Book;

import java.util.List;

public class GetPhysicalBookResponse {

    private String message;
    private int errorCode;
    private boolean status;
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
