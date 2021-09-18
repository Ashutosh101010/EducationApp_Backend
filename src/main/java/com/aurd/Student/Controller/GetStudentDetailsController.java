package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.GetStudentDetailRequest;
import com.aurd.Student.Model.Response.LoginResponse;
import com.aurd.Student.Repository.StudentRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getStudentDetails")
public class GetStudentDetailsController {

    @Inject
    StudentRepository repository;
    @POST
    @Produces(MediaType.APPLICATION_JSON)


    public LoginResponse getStudentDetails(GetStudentDetailRequest request){

        LoginResponse response= new LoginResponse();

        try{
         StudentModel model =  repository.getDetails(request);

         response.setStudent(model);
         response.setMessage("efffd");
         response.setStatusCode(0);
         response.setStatus(true);

        }catch (Exception e){
            System.out.println(e);
            e.printStackTrace();

            response.setMessage("efffd");
            response.setStatusCode(1);
            response.setStatus(false);

        }





        return  response;
    }

}
