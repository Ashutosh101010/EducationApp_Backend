package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Request.testseries.Get_PractiseTestSeries_Request;
import com.aurd.Student.Model.Response.GetCourse_PractiseTest_Response;
import com.aurd.Student.Repository.QuizRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

@Path("/courses/getPractiseTest")
public class GetCoursePractiseController {


    @Inject
    QuizRepository quizRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public GetCourse_PractiseTest_Response getPractiseTest(Get_PractiseTestSeries_Request request){

       ArrayList<QuizModel> arrayList = quizRepository.getMonthlyTest(request.getInst_id(),
               "Monthly Test");

        GetCourse_PractiseTest_Response response= new GetCourse_PractiseTest_Response() ;
        if(arrayList.isEmpty()){
            response.setErrorCode(1);
            response.setMessage("No Practise Test Found");
            response.setStatus(false);
            response.setPractiseTestList(arrayList);
        }else{
            response.setStatus(true);
            response.setMessage("Fetch Practise Test Success");
            response.setPractiseTestList(arrayList);
            response.setErrorCode(0);
        }

   return response;}

}
