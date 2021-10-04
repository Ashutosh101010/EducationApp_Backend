package com.aurd.Student.Model.Request;

import com.aurd.Student.Model.Entity.Quiz_Question_Model;
import com.aurd.Student.Model.Entity.Quiz_Submit_Model;

import java.util.ArrayList;

public class QuizSubmitRequest {
    ArrayList<Quiz_Submit_Model> arrayList = new ArrayList();
    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<Quiz_Submit_Model> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Quiz_Submit_Model> arrayList) {
        this.arrayList = arrayList;
    }
}
