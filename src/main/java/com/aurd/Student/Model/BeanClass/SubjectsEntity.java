package com.aurd.Student.Model.BeanClass;


import com.aurd.Student.Model.Entity.SubSubject;

import java.util.ArrayList;

public class SubjectsEntity {
    private String subject;
    private long id;



    private ArrayList<SubSubject> subSubjectList = new ArrayList();


    public ArrayList<SubSubject> getSubSubjectList() {
        return subSubjectList;
    }

    public void setSubSubjectList(ArrayList<SubSubject> subSubjectList) {
        this.subSubjectList = subSubjectList;
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
