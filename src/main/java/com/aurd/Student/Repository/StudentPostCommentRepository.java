package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Student_Posts_Commented;
import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.aurd.Student.Model.Request.GetCommentRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class StudentPostCommentRepository implements PanacheRepository<Student_Posts_Commented> {

    public ArrayList getComment(GetCommentRequest request){

      ArrayList<Student_Posts_Commented> arrayList = (ArrayList<Student_Posts_Commented>) list("post_id =?1 " ,request.getPost_id());

            return  arrayList;
    }

    public boolean addPostCommentRequest(AddPostCommentRequest request) {
        Student_Posts_Commented studentPostsCommented = new Gson().fromJson(new Gson().toJson(request),Student_Posts_Commented.class);
        persist(studentPostsCommented);
        return true;

    }


}
