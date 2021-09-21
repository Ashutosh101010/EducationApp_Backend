package com.aurd.Student.Controller.TestSeries;


import com.aurd.Student.Model.Request.testseries.Get_PractiseTestSeries_Request;
import com.aurd.Student.Model.Response.TestSeries.PractiseTestSeries_Response;
import com.aurd.Student.Repository.PractiseTestSeriesRepository;
import com.aurd.Student.Repository.TestSeries_Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/courses/getPracticeTest")
public class Get_PractiseTestSeries_Controller {

    @Inject
    PractiseTestSeriesRepository repository;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)

    @Transactional
    public PractiseTestSeries_Response getPractiseTest(Get_PractiseTestSeries_Request request){

        PractiseTestSeries_Response response = new PractiseTestSeries_Response();

        ArrayList arrayList =  repository.getPractiseTest(request.getInst_id(),request.getTopic_id());
        if(arrayList.isEmpty()){
            response.setErrorCode(1);
            response.setStatus(false);
            response.setMessage("No Practice Test Found");
        }else{
            response.setMessage("Practise Test series");
            response.setStatus(true);
            response.setErrorCode(0);
            response.setPractiseTestList(arrayList);
        }


        return  response;
    }

}