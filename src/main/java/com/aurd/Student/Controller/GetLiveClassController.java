package com.aurd.Student.Controller;


import com.aurd.Student.Model.Request.LiveClassesRequest;
import com.aurd.Student.Repository.LiveClassesRepository;

import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/getLiveClassVideos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetLiveClassController {

    @Inject
    LiveClassesRepository repository;
    @POST
    @Transactional

    public void getLiveClasses(LiveClassesRequest request){




    }


}
