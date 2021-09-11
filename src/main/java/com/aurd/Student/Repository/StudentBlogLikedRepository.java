package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Student_Blog_Liked_Model;
import com.aurd.Student.Model.Request.AddStudentBlogLikedRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped

public class StudentBlogLikedRepository implements PanacheRepository <Student_Blog_Liked_Model> {

    public boolean addStudentBlogLikedRequest(AddStudentBlogLikedRequest request) {

        Student_Blog_Liked_Model student_blog_liked_model = new Gson().fromJson(new Gson().toJson(request), Student_Blog_Liked_Model.class);
        persist(student_blog_liked_model);


        return true;
    }
}