package com.aurd.Student.Controller;

import com.aurd.Student.Model.Request.AddStudentPostRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentPostRepository;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.Timestamp;

@Path("/addStudentPost")
public class AddStudentPostController
{

    @Inject
    StudentPostRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public GeneralResponse  addStudentPost(@MultipartForm AddStudentPostRequest request) throws IOException {
        java.util.Date date = new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        request.setAdded_on(timestamp);
        request.setPost_approved_on(timestamp);
        request.setPost_approved_by(0);
        repository.addStudentPost(request);


        GeneralResponse response = new GeneralResponse();
        response.setStatusCode(0);
        response.setStatus(true);
        response.setMessage("Student Post Added Successfully");


        return response;


    }


}
