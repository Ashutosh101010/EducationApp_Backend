package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.RegisterRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterController {

    @Inject
    StudentRepository repository;
    @POST
    @Transactional
    public GeneralResponse register(RegisterRequest request){

        Gson gson = new Gson();
        StudentModel studentModel = gson.fromJson(gson.toJson(request),StudentModel.class);

//        System.out.println("salt ========"+ Integer.toHexString(Integer.parseInt(model.getPassword())));
//        Integer.toHexString(Integer.parseInt(model.getPassword()));
        repository.persist(studentModel);
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setMessage("Register Success");
        generalResponse.setStatus(true);
        generalResponse.setStatusCode(0);
        return  generalResponse;
    }
}
