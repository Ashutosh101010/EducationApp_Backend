package com.aurd.Student.Model.Response;

import com.aurd.Student.Model.BeanClass.StudentCourseEntity;
import java.util.ArrayList;

public class GetStudentCourseResponse {
    ArrayList<StudentCourseEntity> courseList = new ArrayList<>();
    int errorCode;
    boolean status;
    String message;

   public ArrayList<StudentCourseEntity> getCourseList() {
      return courseList;
   }

   public void setCourseList(ArrayList<StudentCourseEntity> courseList) {
       this.courseList = courseList;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
