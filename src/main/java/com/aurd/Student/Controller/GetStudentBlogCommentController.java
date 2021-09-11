package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.Student_Blog_Commented_Model;
import com.aurd.Student.Model.Request.GetStudentBlogCommentRequest;
import com.aurd.Student.Model.Response.GetStudentBlogCommentResponse;
import com.aurd.Student.Repository.StudentBlogCommentRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("/getStudentBlogComment")

public class GetStudentBlogCommentController {

    @Inject
    StudentBlogCommentRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional

    public GetStudentBlogCommentResponse getComments(GetStudentBlogCommentRequest request){

        ArrayList<Student_Blog_Commented_Model> arrayList= repository.getComment(request);
        GetStudentBlogCommentResponse response = new GetStudentBlogCommentResponse();
        if(arrayList.isEmpty()){
            response.setStatusCode(1);
            response.setStatus(false);
            response.setMessage("No comment Found");
        }else {
            response.setComments(arrayList);
            response.setStatusCode(0);
            response.setStatus(true);
            response.setMessage("Get comment Success");
        }


        return  response;
    }



}
