package com.aurd.Student.Controller.forgotPassword;

import com.aurd.Student.Model.Request.UpdatePasswordRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/updatePassword")

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UpdatePassword {

    @Inject
    StudentRepository repository;
    @POST
    @Transactional

    public GeneralResponse updatePassword(UpdatePasswordRequest request){

        GeneralResponse response = new GeneralResponse();
       Query query = repository.getEntityManager().createQuery("update StudentModel StudentModel set " +
                "StudentModel.password =: password where StudentModel .contact =:contact");
       query.setParameter("password",request.getPassword());
       query.setParameter("contact",request.getMobileNumber());

       System.out.println(query.executeUpdate());
       query.executeUpdate();

       response.setStatus(true);
       response.setMessage("Update Success");
       response.seterrorCode(0);
       return  response;


    }



}
