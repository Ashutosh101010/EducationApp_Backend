package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.StudentPostEntity;
import com.aurd.Student.Model.Entity.StudentModel;

import java.util.ArrayList;

public class StudentProfileResponse {
    StudentModel student;
    int errorCode;
    boolean status;
    ArrayList<StudentPostEntity> list = new ArrayList<>();

    public StudentModel getStudent() {
        return student;
    }

    public void setStudent(StudentModel student) {
        this.student = student;
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

    public ArrayList<StudentPostEntity> getList() {
        return list;
    }

    public void setList(ArrayList<StudentPostEntity> list) {
        this.list = list;
    }
}
