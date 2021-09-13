package com.aurd.Student.Model.Response;
import com.aurd.Student.Model.Entity.NoticeModel;
import java.util.ArrayList;

public class GetNoticeResponse {

    String message;
    boolean status;
    int statusCode;
    ArrayList<NoticeModel> notice = new ArrayList();

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

    public ArrayList<NoticeModel> getNotice() {
        return notice;
    }

    public void setNotice(ArrayList<NoticeModel> notice) {
        this.notice = notice;
    }
}
