package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.FunBox;
import com.aurd.Student.Model.Request.FunBoxRequest;
import com.aurd.Student.Model.Response.FunBoxResponse;
import com.aurd.Student.Repository.FunBoxRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/funBox")
public class FunBoxController {

    @Inject
    FunBoxRepository funBoxRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FunBoxResponse funBox(FunBoxRequest request)
    {
        FunBoxResponse response=new FunBoxResponse();
        if(request.getPage()==null || request.getPageSize()==null || request.getInstId()==null)
        {
            response.setStatus(false);
            response.setMessage("Request Invalid");
            response.setErrorCode(1);
            return response;
        }

        List<FunBox> list=funBoxRepository.getFunList(request.getPage(),request.getPageSize(),request.getInstId());

        response.setErrorCode(0);
        response.setStatus(true);
        response.setMessage("FunBox");
        response.setFunBoxList(list);


        return response;



    }


}
