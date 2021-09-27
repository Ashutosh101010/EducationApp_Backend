package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.Entity.PractiseTestSeriesModel;
import com.aurd.Student.Model.Request.testseries.Get_PractiseTestSeries_Request;
import com.aurd.Student.Model.Response.TestSeries.PractiseTestSeries_Response;
import com.aurd.Student.Repository.PractiseTestSeriesRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/testSeries/getPractiseTest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestSeries_PractiseTestController {

    @Inject
    PractiseTestSeriesRepository repository;


    @POST
    @Transactional
    public PractiseTestSeries_Response getPractiseTet(Get_PractiseTestSeries_Request request){

       ArrayList<PractiseTestSeriesModel> arrayList =  repository.getTestSeries_PractiseTest(request);


       PractiseTestSeries_Response response = new PractiseTestSeries_Response();


        response.setPractiseTestList(arrayList);
        response.setStatus(true);
        response.setErrorCode(0);
        response.setMessage("Get Practise Test Success");
        return response;

    }


}
