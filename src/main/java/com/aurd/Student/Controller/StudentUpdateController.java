package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.UpDateRequest;
import com.aurd.Student.Model.Response.UpdateResponse;
import com.aurd.Student.Repository.StudentRepository;


import javax.inject.Inject;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/UpdateStudents")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class StudentUpdateController {
    @Inject
    StudentRepository repository;
    @POST

    @Transactional


    public UpdateResponse update(UpDateRequest request) {

      // repository.findById(request.getStudent_id());
        Integer studentModel  = repository.UpDateRequest(request);
        System.out.println(studentModel);
        UpdateResponse response = new UpdateResponse();
        if(studentModel>0)
        {
          response.setMessage("update Success");
          response.setStatus(true);
          response.setStatusCode(0);
        }
        else{
            response.setMessage("update Fail");
            response.setStatus(false);
            response.setStatusCode(1);
        }
       return response;
    }
    }
