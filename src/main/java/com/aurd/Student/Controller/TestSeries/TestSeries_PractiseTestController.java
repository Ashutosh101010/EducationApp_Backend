package com.aurd.Student.Controller.TestSeries;

import com.aurd.Student.Model.Entity.PractiseTestSeriesModel;
import com.aurd.Student.Model.Request.testseries.Get_PractiseTestSeries_Request;
import com.aurd.Student.Model.Response.TestSeries.PractiseTestSeries_Response;
import com.aurd.Student.Repository.PractiseTestSeriesRepository;
import com.aurd.Student.Repository.TestSeriesResultRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/testSeries/getPractiseTest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestSeries_PractiseTestController {

    @Inject
    PractiseTestSeriesRepository repository;

    @Inject
    TestSeriesResultRepository testSeriesResultRepository;


    @POST
    public PractiseTestSeries_Response getPractiseTet(Get_PractiseTestSeries_Request request){

       ArrayList<PractiseTestSeriesModel> arrayList =
               repository.getTestSeries_PractiseTest(request);


       arrayList.forEach(practiseTestSeriesModel -> {


           Query query=testSeriesResultRepository.getEntityManager().createQuery("select TestSeriesResult from TestSeriesResult TestSeriesResult where TestSeriesResult.inst_id=:instId and TestSeriesResult.stud_id=:stud_id and TestSeriesResult.quiz_id=:quizId");
         query.setParameter("instId",request.getInst_id());
         query.setParameter("stud_id",request.getUser_id());
         query.setParameter("quizId",Long.parseLong(String.valueOf(practiseTestSeriesModel.getId())));




           List list= query.getResultList();
         if(!list.isEmpty())
         {
             practiseTestSeriesModel.setAttempt(true);
         }


       });

       System.out.println(arrayList);
       PractiseTestSeries_Response response = new PractiseTestSeries_Response();


        response.setPractiseTestList(arrayList);
        response.setStatus(true);
        response.setErrorCode(0);
        response.setMessage("Get Practise Test Success");
        return response;

    }
 
    
    @Path("/{testName}")
    @POST
    public PractiseTestSeries_Response getPractiseTestByTest(Get_PractiseTestSeries_Request request, @PathParam("testName") String testName){

       ArrayList<PractiseTestSeriesModel> arrayList =
               repository.getTestSeries_PractiseTestByTest(request,testName);


       arrayList.forEach(practiseTestSeriesModel -> {


           Query query=testSeriesResultRepository.getEntityManager().createQuery("select TestSeriesResult from TestSeriesResult TestSeriesResult where TestSeriesResult.inst_id=:instId and TestSeriesResult.stud_id=:stud_id and TestSeriesResult.quiz_id=:quizId");
         query.setParameter("instId",request.getInst_id());
         query.setParameter("stud_id",request.getUser_id());
         query.setParameter("quizId",Long.parseLong(String.valueOf(practiseTestSeriesModel.getId())));




           List list= query.getResultList();
         if(!list.isEmpty())
         {
             practiseTestSeriesModel.setAttempt(true);
         }


       });
       PractiseTestSeries_Response response = new PractiseTestSeries_Response();


        response.setPractiseTestList(arrayList);
        response.setStatus(true);
        response.setErrorCode(0);
        response.setMessage("Get Practise Test Success");
        return response;

    }
 @Path("/series")
    @POST
    public PractiseTestSeries_Response getPractiseTestBySeries(Get_PractiseTestSeries_Request request){

       ArrayList<PractiseTestSeriesModel> arrayList =
               repository.getTestSeries_PractiseTestBySeries(request);


       arrayList.forEach(practiseTestSeriesModel -> {


           Query query=testSeriesResultRepository.getEntityManager().createQuery("select TestSeriesResult from TestSeriesResult TestSeriesResult where TestSeriesResult.inst_id=:instId and TestSeriesResult.stud_id=:stud_id and TestSeriesResult.quiz_id=:quizId");
         query.setParameter("instId",request.getInst_id());
         query.setParameter("stud_id",request.getUser_id());
         query.setParameter("quizId",Long.parseLong(String.valueOf(practiseTestSeriesModel.getId())));




           List list= query.getResultList();
         if(!list.isEmpty())
         {
             practiseTestSeriesModel.setAttempt(true);
         }


       });
       PractiseTestSeries_Response response = new PractiseTestSeries_Response();


        response.setPractiseTestList(arrayList);
        response.setStatus(true);
        response.setErrorCode(0);
        response.setMessage("Get Practise Test Success");
        return response;

    }


}
