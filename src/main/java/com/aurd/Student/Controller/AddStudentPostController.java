package com.aurd.Student.Controller;

import com.aurd.Student.Model.Request.AddStudentPostRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentPostRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;

@Path("/addStudentPost")
public class AddStudentPostController
{

    @Inject
    StudentPostRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional
    public GeneralResponse  addStudentPost(AddStudentPostRequest request){
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        request.setAdded_on(timestamp);

        repository.addStudentPost(request);


        GeneralResponse response = new GeneralResponse();
        response.setStatusCode(0);
        response.setStatus(true);
        response.setMessage("Student Post Added Successfully");


        return response;


    }


}
