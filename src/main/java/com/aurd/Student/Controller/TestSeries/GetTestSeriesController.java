package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.TestSeriesModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Response.GetQuizResponse;
import com.aurd.Student.Model.Response.TestSeries.TestSeriesResponse;
import com.aurd.Student.Repository.QuizRepository;
import com.aurd.Student.Repository.TestSeries_Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;

@Path("/testSeries/getTestSeries")
public class GetTestSeriesController {

    @Inject
    TestSeries_Repository repository;
    @POST
    @Transactional

    public TestSeriesResponse getTestSeries(GetQuizRequest request){

    ArrayList<TestSeriesModel> testSeries = repository.getAllTestSeries(request.getInst_id());

        TestSeriesResponse response = new TestSeriesResponse();
        if(testSeries.isEmpty()){
            response.setStatus(false);
            response.setErrorCode(1);
            response.setMessage("No Test Series Found");
        }else{
            response.setTestSeriesList(testSeries);
            response.setStatus(true);
            response.setErrorCode(0);
            response.setMessage("Get test series success");
        }



       return response;

    }
}
