package com.aurd.Student.Controller;



import com.aurd.Student.Model.Entity.StudentModel;
import com.aurd.Student.Model.Request.GetProfileRequest;

import com.aurd.Student.Model.Response.GetProfileResponse;

import com.aurd.Student.Repository.StudentRepository;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/Profile")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)


public class GetProfileController {
    @Inject
    StudentRepository repository;


    @Transactional
    @POST


    public GetProfileResponse getProfile(GetProfileRequest request) {

       StudentModel model = repository.find("id=?1",request.getId()).firstResult();
       System.out.println(model);


        GetProfileResponse getProfileResponse = new GetProfileResponse();
        getProfileResponse.setStudentModel(model);
        getProfileResponse.setMessage("Get Profile Successfully");
        getProfileResponse.setStatus(true);
        getProfileResponse.seterrorCode(0);

        return getProfileResponse;

    }
    }



