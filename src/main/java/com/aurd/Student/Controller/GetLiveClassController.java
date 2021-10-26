package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.LiveClassModel;
import com.aurd.Student.Model.Request.LiveClassesRequest;
import com.aurd.Student.Model.Response.GetLiveClassResponse;
import com.aurd.Student.Repository.LiveClassesRepository;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getLiveClasses")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetLiveClassController {

    @Inject
    LiveClassesRepository repository;
    @POST
    @Transactional

    public GetLiveClassResponse getLiveClasses(LiveClassesRequest request){

       ArrayList<LiveClassModel> arrayList = repository.getLiveSessions(request);


        GetLiveClassResponse response = new GetLiveClassResponse();
        if(arrayList.isEmpty()){
            response.setMessage("No Live Class Found");
            response.setErrorCode(1);
            response.setStatus(true);
            response.setList(arrayList);
        }else{
            response.setList(arrayList);
            response.setStatus(true);
            response.setErrorCode(0);
            response.setMessage("Live Class found successfully");
        }

        return response;
    }


}
