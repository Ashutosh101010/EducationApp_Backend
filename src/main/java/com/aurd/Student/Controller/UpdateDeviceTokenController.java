package com.aurd.Student.Controller;

import com.aurd.Student.Model.Request.DeviceTokenRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Model.Response.StudentPostsResponse;
import com.aurd.Student.Repository.StudentRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/updateDeviceToken")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UpdateDeviceTokenController {
    @Inject
    StudentRepository studentsRepository;

    @POST
    @Transactional
    public GeneralResponse updateToken(DeviceTokenRequest request) {
        GeneralResponse response = new GeneralResponse();
        studentsRepository.addDeviceToken(request);
        response.setMessage("Device Token Added");
        response.setStatus(true);
        response.seterrorCode(0);
        return response;

    }




    @Path("/removeDeviceToken")
    @Transactional
    public GeneralResponse removeToken(DeviceTokenRequest request){
        studentsRepository.removeDeviceToken(request);

        GeneralResponse response = new GeneralResponse();
        response.seterrorCode(0);
        response.setMessage("Device Token Removed");
        response.setStatus(true);

        return  response;

    }

}
