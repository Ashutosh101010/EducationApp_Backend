package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.Report;
import com.aurd.Student.Model.Request.ReportRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.ReportRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/report")
public class ReportController {

    @Inject
    ReportRepository repository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GeneralResponse report(ReportRequest request)
    {
        GeneralResponse response=new GeneralResponse();
       repository.addReport(request);
       response.seterrorCode(0);
       response.setMessage("SUCCESS");
       response.setStatus(true);

       return response;
    }
}
