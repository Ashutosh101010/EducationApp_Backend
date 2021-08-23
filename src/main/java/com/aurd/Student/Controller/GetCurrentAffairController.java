package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.CurrentAffairModel;
import com.aurd.Student.Model.Request.GetCurrentAffairRequest;
import com.aurd.Student.Model.Response.GetCurrentAffairResponse;
import com.aurd.Student.Repository.CurrentAffairRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/getCurrentAffair")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetCurrentAffairController {

    @Inject
    CurrentAffairRepository repository;


    @POST

    @Transactional
    public GetCurrentAffairResponse getList(GetCurrentAffairRequest request){


        ArrayList<CurrentAffairModel> list = repository.getCurrentAffairList(request);

        GetCurrentAffairResponse getCurrentAffairResponse = new GetCurrentAffairResponse();



        if(list.isEmpty()){

            getCurrentAffairResponse.setMessage("No Current Affairs");
            getCurrentAffairResponse.setStatus(false);
            getCurrentAffairResponse.setStatusCode(1);

        }else{
            getCurrentAffairResponse.setCurrentAffair(list);
            getCurrentAffairResponse.setMessage("Get Current Affair Success");
            getCurrentAffairResponse.setStatus(true);
            getCurrentAffairResponse.setStatusCode(0);
        }


        return  getCurrentAffairResponse;

    }


}
