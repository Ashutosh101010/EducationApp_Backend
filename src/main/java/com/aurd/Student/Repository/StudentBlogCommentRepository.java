package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Student_Blog_Commented_Model;
import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Request.AddStudentBlogCommentRequest;
import com.aurd.Student.Model.Request.GetStudentBlogCommentRequest;
import com.aurd.Student.Model.Request.StudentPostCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped

public class StudentBlogCommentRepository implements PanacheRepository<Student_Blog_Commented_Model> {

    public boolean addStudentBlogCommentRequest (AddStudentBlogCommentRequest request) {

        Student_Blog_Commented_Model student_blog_commented_model = new Gson().fromJson(new Gson().toJson(request),Student_Blog_Commented_Model.class);
        persist(student_blog_commented_model);
        return true;

    }

    public ArrayList getComment(GetStudentBlogCommentRequest request){

        ArrayList<Student_Blog_Commented_Model> arrayList = (ArrayList<Student_Blog_Commented_Model>) list("blog_id =?1 " ,request.getBlog_id());

        return  arrayList;
    }


}







