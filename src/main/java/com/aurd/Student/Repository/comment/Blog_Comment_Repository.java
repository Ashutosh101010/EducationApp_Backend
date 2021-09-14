package com.aurd.Student.Repository.comment;

import com.aurd.Student.Model.Entity.Student_Blog_Commented_Model;
import com.aurd.Student.Model.Request.AddBlogCommentRequest;
import com.aurd.Student.Model.Request.GetBlogAndCurrentAffairCommentRequest;
import com.aurd.Student.Model.Request.GetCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped

public class Blog_Comment_Repository implements PanacheRepository<Student_Blog_Commented_Model> {

    public boolean addStudentBlogCommentRequest (AddBlogCommentRequest request) {

        Student_Blog_Commented_Model student_blog_commented_model = new Gson().fromJson(new Gson().toJson(request),Student_Blog_Commented_Model.class);
        persist(student_blog_commented_model);
        return true;

       }

    public ArrayList getComment(GetCommentRequest request){

        ArrayList<Student_Blog_Commented_Model> arrayList = (ArrayList<Student_Blog_Commented_Model>)
                find("blog_id" ,request.getPost_id()).list();

        return  arrayList;
      }


     }







