package com.aurd.Student.Model.Response;
import com.aurd.Student.Model.Entity.NoticeModel;
import java.util.ArrayList;

public class GetNoticeResponse {

    String message;
    boolean status;
    int errorCode;
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

    public int geterrorCode() {
        return errorCode;
    }

    public void seterrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ArrayList<NoticeModel> getNotice() {
        return notice;
    }

    public void setNotice(ArrayList<NoticeModel> notice) {
        this.notice = notice;
    }
}
