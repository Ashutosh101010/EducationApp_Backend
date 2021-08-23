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

@Path("/getQuiz")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GetQuizController {

    @Inject
    QuizRepository repository;
    @POST
    @Transactional
    public GetQuizResponse getQuizzes(GetQuizRequest request){

     ArrayList<QuizModel> arrayList =   repository.getQuizzes(request);
     GetQuizResponse getQuizResponse = new GetQuizResponse();
     getQuizResponse.setQuizList(arrayList);
     getQuizResponse.setErrorCode(0);
     getQuizResponse.setMessage("Get Quiz");
     getQuizResponse.setStatus(true);


     return  getQuizResponse;

    }


}
