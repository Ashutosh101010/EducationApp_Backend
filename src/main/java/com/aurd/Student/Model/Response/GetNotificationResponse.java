package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;


public class GetNotificationResponse {

    String message;
    int errorCode;
    boolean status;
    ArrayList<Notification> notifications = new ArrayList<>();



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

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }
}
