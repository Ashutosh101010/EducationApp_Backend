package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.Quiz_Submit_Model;
import com.aurd.Student.Model.Request.QuizSubmitRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.Quiz_Submit_Repository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/submitQuiz")
public class QuizSubmitController {


    @Inject
    Quiz_Submit_Repository repository;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public GeneralResponse submitQuiz(QuizSubmitRequest request){
        GeneralResponse response = new GeneralResponse();
       boolean value = repository.submitStudentQuizResponses(request);
        if(value){
            response.setMessage("Your Quiz is Submitted !Result will be shown soon");
            response.setStatus(true);
            response.setStatusCode(0);
        }



        System.out.println(request.getArrayList());


        return   response;

    }
}
