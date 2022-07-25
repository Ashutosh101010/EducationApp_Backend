package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.DigitalWorld;
import com.aurd.Student.Model.Request.DigitalWorldFolderRequest;
import com.aurd.Student.Model.Response.DigitalWorldFolderResponse;
import com.aurd.Student.Repository.DigitalWorldFolderRepository;
import org.jboss.logging.annotations.Pos;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/digitalWorld")
public class DigitalWorldController {


    @Inject
    DigitalWorldFolderRepository repository;
    @POST
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

        List<DigitalWorld> list =repository.find("inst_id",request.getInstId()).list();

        response.setStatus(true);
        response.setErrorCode(0);
        response.setFolderList(list);
        return response;
    }
}
