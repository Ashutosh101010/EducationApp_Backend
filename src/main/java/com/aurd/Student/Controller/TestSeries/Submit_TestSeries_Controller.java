package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.Request.testseries.Submit_PracticeTest_Request;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.TestSeries_Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/testSeries/submitTestSeries")

public class Submit_TestSeries_Controller {


    @Inject
    TestSeries_Repository repository;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public GeneralResponse submitPracticeTest(Submit_PracticeTest_Request request){

        repository.addPracticeTest(request);

        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setStatus(true);
        generalResponse.setMessage("Practice Test Series Submitted");
        generalResponse.setStatusCode(0);
        return generalResponse;
    }
}
