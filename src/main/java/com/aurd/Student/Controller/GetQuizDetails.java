package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Request.GetQuizDetailRequest;
import com.aurd.Student.Model.Response.GetQuizDetailResponse;
import com.aurd.Student.Repository.QuizRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/getQuizDetails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetQuizDetails {

    @Inject
    QuizRepository quizRepository;

    @POST


    public GetQuizDetailResponse getDetails(GetQuizDetailRequest request) {

     QuizModel quizModel =  quizRepository.find("quiz_id =?1 and inst_id =?2",request.getQuiz_id(),request.getInst_id()).firstResult();

        GetQuizDetailResponse response = new GetQuizDetailResponse();
        response.setQuizModel(quizModel);
        response.setMessage("Get Quiz Detail");
        response.setErrorCode(0);
        response.setStatus(true);
        return  response;

    }
}


