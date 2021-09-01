package com.aurd.Student.Controller.MonthlyTest;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Response.GetQuizResponse;
import com.aurd.Student.Model.Response.TestSeries.MonthlytestSeriesResponse;
import com.aurd.Student.Repository.QuizRepository;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/getMonthlyTest")
public class GetMonthlyTestController {

    @Inject
    QuizRepository repository;
    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public MonthlytestSeriesResponse getMonthlyTestSeries(@QueryParam("instID") long instID, @QueryParam("type") String type){
        ArrayList<QuizModel> testList = repository.getMonthlyTest(instID,type);

        MonthlytestSeriesResponse response = new MonthlytestSeriesResponse();
        response.setMessage("Fetch Monthly Test Successful");
        response.setStatus(true);
        response.setTestList(testList);
        response.setErrorCode(0);
      return   response;
    }

}
