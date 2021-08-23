package com.aurd.Student.Model.BeanClass;

import com.aurd.Student.Model.Entity.SubSubjectModel;

import java.util.ArrayList;

public class SubjectsEntity {
    private String subject;
    private long id;
    private ArrayList<SubSubjectModel> arrayList = new ArrayList();


    public ArrayList<SubSubjectModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<SubSubjectModel> arrayList) {
        this.arrayList = arrayList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
