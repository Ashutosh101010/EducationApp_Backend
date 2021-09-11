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

    public void addStudentPost(AddStudentPostRequest request) throws IOException {
        StudentPostModel studentPostModel = new StudentPostModel();
        studentPostModel.setAdded_on(request.getAdded_on());
        studentPostModel.setAdded_by(request.getAdded_by());
        String myString = IOUtils.toString(request.getPic(), "utf8mb4_unicode_ci");
        System.out.println(myString);
        studentPostModel.setPic(myString);
        studentPostModel.setDiscription(request.getDiscription());
        studentPostModel.setPost_status(request.getPost_status());
        studentPostModel.setPost_approved_by(0);

        persist(studentPostModel);
        System.out.println(new Gson().toJson(studentPostModel));
    }

    public ArrayList getStudentPosts(GetStudentPostRequest request){

        ArrayList<StudentPostModel> arrayList = (ArrayList<StudentPostModel>) list("added_by =?1 and inst_id =?2",
                request.getStud_id(),request.getInst_id());

        return  arrayList;



    }
}
