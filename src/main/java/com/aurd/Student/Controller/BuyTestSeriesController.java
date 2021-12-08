package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.StudentTestModel;
import com.aurd.Student.Model.Request.BuyTestSeriesRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentTestRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/buyTestSeries")


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BuyTestSeriesController {

    @Inject
    StudentTestRepository repository;


    @POST
    @Transactional

    public GeneralResponse buyTestSeries(BuyTestSeriesRequest request){

        StudentTestModel model = new StudentTestModel();
        model.setTest_series_id(String.valueOf(request.getTest_series_id()));
        model.setStudent(request.getStud_id());


        repository.persist(model);
        repository.flush();

        GeneralResponse response = new GeneralResponse();
        response.setMessage("Buy Successful");
        response.setStatus(true);
        response.seterrorCode(0);
        return  response;
    }
}
