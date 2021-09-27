package com.aurd.Student.Repository.comment;


import com.aurd.Student.Model.Entity.Blog_Comment_Model;
import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.aurd.Student.Model.Request.GetCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped

public class Blog_Comment_Repository implements PanacheRepository<Blog_Comment_Model> {

    public boolean addStudentBlogCommentRequest (AddPostCommentRequest request) {

        Blog_Comment_Model student_blog_commented_model = new Gson().fromJson(new Gson().toJson(request),Blog_Comment_Model.class);
        student_blog_commented_model.setBlog_id(request.getPost_id());
        persist(student_blog_commented_model);
        return true;

       }

    public ArrayList getComment(GetCommentRequest request){

        ArrayList<Blog_Comment_Model> arrayList = (ArrayList<Blog_Comment_Model>)
                find("blog_id",request.getPost_id()).list();

        return  arrayList;
      }


     }







