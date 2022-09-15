package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.Entity.*;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Request.testseries.Get_TestSeries_Request;
import com.aurd.Student.Model.Response.TestSeries.PractiseTestSeries_Response;
import com.aurd.Student.Model.Response.TestSeries.TestSeriesResponse;
import com.aurd.Student.Repository.*;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/testSeries/getTestSeries")
public class GetTestSeriesController {

    @Inject
   // TestSeries_Repository repository;
    QuizRepository quizRepository;

    @Inject
    PurchaseTestRepository repository;
    @Inject
    PractiseTestSeriesRepository practiseTestSeriesRepository;

    @Inject
    TestSeriesResultRepository testSeriesResultRepository;



    @POST


       public TestSeriesResponse getTestSeries(Get_TestSeries_Request request){

        TestSeriesResponse response = new TestSeriesResponse();


        ArrayList<QuizModel>testSeries = quizRepository.
                getAllTestSeries(request.getInst_id(),request.getType());


        testSeries.forEach(quizModel -> {
//          StudentTestModel model = testRepository.find("test_series_id =?1 and student =?2",
//                    quizModel.getQuiz_id().toString(),Long.valueOf(request.getStud_id()).intValue()).firstResult();

            PurchaseTestModel purchaseTestModel=repository.find("test_seriesId=?1 and stud_id=?2", ((int) request.getStud_id()),quizModel.getQuiz_id()).firstResult();

            List<TestEntity> testList=new ArrayList<>();
            String[] test=quizModel.getTest().substring(1,quizModel.getTest().length()-1).split(",");
            for(int i=0;i<test.length;i++)
            {
                PractiseTestSeriesModel practiseTestSeriesModel=practiseTestSeriesRepository.find("series_id=?1 and test=?2",quizModel.getQuiz_id(),test[i]).firstResult();

                if(practiseTestSeriesModel!=null)
                {
                    System.out.println("found");
                    TestEntity testEntity=new TestEntity();
                    testEntity.setName(practiseTestSeriesModel.getTest());
                    Query query=testSeriesResultRepository.getEntityManager().createQuery("select TestSeriesResult from TestSeriesResult TestSeriesResult where TestSeriesResult.inst_id=:instId and TestSeriesResult.stud_id=:stud_id and TestSeriesResult.quiz_id=:quizId");
                    query.setParameter("instId", ((long) request.getInst_id()));
                    query.setParameter("stud_id",request.getStud_id());
                    query.setParameter("quizId",Long.parseLong(String.valueOf(practiseTestSeriesModel.getId())));




                    List list= query.getResultList();
                    if(!list.isEmpty())
                    {
                        practiseTestSeriesModel.setAttempt(true);
                    }
                    testEntity.setAttempt(practiseTestSeriesModel.isAttempt());

                    testList.add(testEntity);

                }


            }
//            List<PractiseTestSeriesModel> practiseTestSeriesModelList=practiseTestSeriesRepository.find("series_id=?1 and test=?2",quizModel.getQuiz_id()).list();
//
//
//
//            practiseTestSeriesModelList.forEach(practiseTestSeriesModel -> {
//
//
//                Query query=testSeriesResultRepository.getEntityManager().createQuery("select TestSeriesResult from TestSeriesResult TestSeriesResult where TestSeriesResult.inst_id=:instId and TestSeriesResult.stud_id=:stud_id and TestSeriesResult.quiz_id=:quizId");
//                query.setParameter("instId", ((long) request.getInst_id()));
//                query.setParameter("stud_id",request.getStud_id());
//                query.setParameter("quizId",Long.parseLong(String.valueOf(practiseTestSeriesModel.getId())));
//
//
//
//
//                List list= query.getResultList();
//                if(!list.isEmpty())
//                {
//                    practiseTestSeriesModel.setAttempt(true);
//                }


//            });

            quizModel.setTestList(testList);



          if(purchaseTestModel==null){
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
