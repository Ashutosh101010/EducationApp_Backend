package com.aurd.Student.Model.Request;

import com.aurd.Student.Model.Entity.Quiz_Question_Model;

import java.util.ArrayList;

public class QuizSubmitRequest {
    ArrayList<Quiz_Question_Model> arrayList = new ArrayList();

    public ArrayList getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }
}
