package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.StudentTestModel;
import com.aurd.Student.Model.Entity.TestSeriesModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Request.testseries.Get_TestSeries_Request;
import com.aurd.Student.Model.Response.TestSeries.PractiseTestSeries_Response;
import com.aurd.Student.Model.Response.TestSeries.TestSeriesResponse;
import com.aurd.Student.Repository.QuizRepository;
import com.aurd.Student.Repository.StudentTestRepository;
import com.aurd.Student.Repository.TestSeries_Repository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;

@Path("/testSeries/getTestSeries")
public class GetTestSeriesController {

    @Inject
   // TestSeries_Repository repository;
    QuizRepository quizRepository;

    @Inject
    StudentTestRepository testRepository;

    @POST


       public TestSeriesResponse getTestSeries(Get_TestSeries_Request request){

        TestSeriesResponse response = new TestSeriesResponse();


        ArrayList<QuizModel>testSeries = quizRepository.
                getAllTestSeries(request.getInst_id(),request.getType());


        testSeries.forEach(quizModel -> {
          StudentTestModel model = testRepository.find("test_series_id =?1 and student =?2",
                    quizModel.getQuiz_id().toString(),Long.valueOf(request.getStud_id()).intValue()).firstResult();

          if(model==null){
              quizModel.setTestSeriesPurchased(false);
          }else{
              quizModel.setTestSeriesPurchased(true);
          }
        });


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
