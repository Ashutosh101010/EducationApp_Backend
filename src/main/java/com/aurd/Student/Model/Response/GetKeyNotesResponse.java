package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.KeyNotesModel;
import java.util.ArrayList;
public class GetKeyNotesResponse {



    String message;
    boolean status;
    int errorCode;
    ArrayList<KeyNotesModel> keynotes = new ArrayList();

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

    public ArrayList<KeyNotesModel> getKeynotes() {
        return keynotes;
    }

    public void setKeynotes(ArrayList<KeyNotesModel> keynotes) {
        this.keynotes = keynotes;
    }
}
