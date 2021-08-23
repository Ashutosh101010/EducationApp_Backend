package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Request.AddStudentPostRequest;
import com.aurd.Student.Model.Request.GetStudentPostRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class StudentPostRepository implements PanacheRepository<StudentPostModel> {

    public void addStudentPost(AddStudentPostRequest request){
        StudentPostModel studentPostModel = new Gson().fromJson(new Gson().toJson(request),StudentPostModel.class);
        persist(studentPostModel);
    }

    public ArrayList getStudentPosts(GetStudentPostRequest request){

        ArrayList<StudentPostModel> arrayList = (ArrayList<StudentPostModel>) list("added_by =?1 and inst_id =?2",
                request.getStud_id(),request.getInst_id());

        return  arrayList;



    }
}
