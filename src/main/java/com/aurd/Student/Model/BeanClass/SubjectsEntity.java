package com.aurd.Student.Model.BeanClass;

import com.aurd.Student.Model.Entity.SubSubjectModel;

import java.util.ArrayList;

public class SubjectsEntity {
    private String subject;
    private long id;
    private ArrayList<SubSubjectEntity> subjectList = new ArrayList();

    public ArrayList<SubSubjectEntity> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(ArrayList<SubSubjectEntity> subjectList) {
        this.subjectList = subjectList;
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
