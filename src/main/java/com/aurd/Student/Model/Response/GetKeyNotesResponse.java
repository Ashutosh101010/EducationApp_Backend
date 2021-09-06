package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.Entity.KeyNotesModel;
import java.util.ArrayList;
public class GetKeyNotesResponse {



    String message;
    boolean status;
    int statusCode;
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

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ArrayList<KeyNotesModel> getKeynotes() {
        return keynotes;
    }

    public void setKeynotes(ArrayList<KeyNotesModel> keynotes) {
        this.keynotes = keynotes;
    }
}
