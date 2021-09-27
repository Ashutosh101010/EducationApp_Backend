package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Response.GetQuizResponse;
import com.aurd.Student.Repository.QuizRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/institute/getPractiseTest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetAllPractiseTestController {
    @Inject
    QuizRepository quizRepository;

    @POST
    @Transactional

    public GetQuizResponse getPractiseTest(GetQuizRequest request){
        ArrayList<QuizModel> arrayList =   quizRepository.getQuizzes(request);
        GetQuizResponse getQuizResponse = new GetQuizResponse();
        getQuizResponse.setQuizList(arrayList);
        getQuizResponse.setErrorCode(0);
        getQuizResponse.setMessage("Get Practise Test Successfully");
        getQuizResponse.setStatus(true);

        return  getQuizResponse;

    }

}
