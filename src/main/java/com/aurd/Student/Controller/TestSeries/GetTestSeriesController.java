package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Response.GetQuizResponse;
import com.aurd.Student.Repository.QuizRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;

@Path("/testSeries/getTestSeries")
public class GetTestSeriesController {

    @Inject
    QuizRepository repository;
    @POST
    @Transactional

    public GetQuizResponse getTestSeries(GetQuizRequest request){

        ArrayList<QuizModel> arrayList = repository.getTestSeries(request);


        GetQuizResponse response = new GetQuizResponse();
        response.setQuizList(arrayList);
        response.setStatus(true);
        response.setErrorCode(0);
        response.setMessage("Get test series success");
       return response ;

    }
}
