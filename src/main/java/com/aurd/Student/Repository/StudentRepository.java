package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.LoginRequest;
import com.aurd.Student.Model.Response.LoginResponse;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class StudentRepository implements PanacheRepository<StudentModel> {

    public StudentModel login(LoginRequest request){
        return  find("email=?1 and password = ?2",request.getEmail(),request.getPassword()).firstResult();
    }

}
