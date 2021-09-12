package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.StudentPostModel;
import com.aurd.Student.Model.Request.AddStudentPostRequest;
import com.aurd.Student.Model.Request.GetStudentPostRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.apache.commons.io.IOUtils;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.ArrayList;

@ApplicationScoped
public class StudentPostRepository implements PanacheRepository<StudentPostModel> {

    public void addStudentPost(StudentPostModel model) {
        System.out.println("Repository Class Call");

        System.out.println(new Gson().toJson(model));
      try{
          persist(model);
      }catch (Exception e){
          System.out.println(e);
      }
    }

    public ArrayList getStudentPosts(GetStudentPostRequest request){

        ArrayList<StudentPostModel> arrayList = (ArrayList<StudentPostModel>) list("added_by =?1 and inst_id =?2",
                request.getStud_id(),request.getInst_id());

        return  arrayList;



    }
}
