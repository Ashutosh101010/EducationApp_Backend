package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.NotificationModel;

import java.util.ArrayList;

public class GetNotificationResponse {

    String message;
    int errorCode;
    boolean status;
    ArrayList<NotificationModel> notifications = new ArrayList<>();

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

    public ArrayList<NotificationModel> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<NotificationModel> notifications) {
        this.notifications = notifications;
    }
}
