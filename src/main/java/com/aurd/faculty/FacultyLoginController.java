package com.aurd.faculty;

import com.aurd.Student.Model.Entity.TeacherModel;
import com.aurd.Student.Model.Request.LoginRequest;
import com.aurd.Student.Repository.TeacherRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/employee/login")
public class FacultyLoginController {
    @Inject
    TeacherRepository repository;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public void login(LoginRequest request){

       TeacherModel teacherModel =  repository.find("email=?1 and password = ?2", request.getEmail(),request.getPassword()).firstResult();
       if(teacherModel==null){

       }

    }
}
