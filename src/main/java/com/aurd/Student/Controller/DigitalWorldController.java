package com.aurd.Student.Controller;

//import com.aurd.Student.Model.Entity.DigitalWorld;
import com.aurd.Student.Model.Entity.DigitalWorldMedia;
import com.aurd.Student.Model.Request.DigitalWorldFolderRequest;
import com.aurd.Student.Model.Response.DigitalWorldFolderResponse;
import com.aurd.Student.Model.Response.DigitalWorldMediaResponse;
//import com.aurd.Student.Repository.DigitalWorldFolderRepository;
import com.aurd.Student.Repository.DigitalWorldMediaRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/digitalWorld")
public class DigitalWorldController {


//    @Inject
//    DigitalWorldFolderRepository repository;

    @Inject
    DigitalWorldMediaRepository digitalWorldMediaRepository;

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DigitalWorldFolderResponse digitalWorld(DigitalWorldFolderRequest request)
    {
        DigitalWorldFolderResponse response=new DigitalWorldFolderResponse();
        if(request.getInstId()==null)
        {
            response.setErrorCode(1);
            response.setStatus(false);
            response.setMessage("Invalid Request");
            return response;
        }


        List<DigitalWorldMedia> mediaList=digitalWorldMediaRepository.digitalWorldMedia((long) -1,request.getInstId());


        response.setStatus(true);
        response.setErrorCode(0);
        response.setMediaList(mediaList);
        return response;
    }


    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public DigitalWorldMediaResponse digitalWorldById(@PathParam("id") String id)
    {
        DigitalWorldMediaResponse response=new DigitalWorldMediaResponse();


        List<DigitalWorldMedia> list =digitalWorldMediaRepository.find("folder_id",Long.valueOf(id)).list();

        response.setStatus(true);
        response.setErrorCode(0);
        response.setMediaList(list);
        return response;
    }
}
