package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.LoginRequest;
import com.aurd.Student.Model.Response.LoginResponse;
import com.aurd.Student.Repository.StudentRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)


public class LoginController {

    @Inject
    StudentRepository repository;



    @POST
    public LoginResponse loginStudent(LoginRequest request){



       StudentModel studentModel  = repository.login(request);
        LoginResponse response = new LoginResponse();
       if(studentModel!=null){
           System.out.println("sdawdewdscdsf       " +studentModel.isIs_active());
           if(studentModel.isIs_active()==false){
               if(studentModel.getPassword().equals(request.getPassword())){
                   response.setStudent(studentModel);
                   response.setMessage("Login Success");
                   response.setStatus(true);
                   response.seterrorCode(0);
               }else{
                   response.setMessage("Login Fail! Password does not matches");
                   response.setStatus(false);
                   response.seterrorCode(2);
               }
           }


       }else{
           response.setMessage("Login Failure");
           response.setStatus(false);
           response.seterrorCode(1);
       }




       return response;
    }


}
