package com.aurd.Student.Controller;


import com.aurd.Student.Model.BeanClass.QuizEntity;
import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.SaveResultModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Response.GetQuizResponse;
import com.aurd.Student.Repository.QuizRepository;
import com.aurd.Student.Repository.ResultRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.Query;
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

    @Inject
    ResultRepository resultRepository;

    @POST
    @Transactional
    public GetQuizResponse getQuizzes(GetQuizRequest request){


        System.out.println(new Gson().toJson(request));


     ArrayList<QuizModel> arrayList =   repository.getQuizzes(request);

     ArrayList<QuizEntity> quizList = new ArrayList<>();

     arrayList.forEach(quizModel -> {

         QuizEntity quizEntity = new Gson().fromJson(new Gson().toJson(quizModel),QuizEntity.class);

         String string = "SELECT * FROM `result` WHERE inst_id= ? and stud_id = ? and quiz_id=?";
         Query query = resultRepository.getEntityManager().createNativeQuery(string);
         query.setParameter(1, request.getInst_id());
         query.setParameter(2,request.getStud_id());
         query.setParameter(3,quizModel.getQuiz_id());

         System.out.println(query);

         ArrayList<Object[]> rList = (ArrayList<Object[]>) query.getResultList();
         rList.forEach(objects -> {
             System.out.println(objects[0].toString());
             System.out.println(objects[1].toString());
             System.out.println(request.getStud_id());


             if(Integer.parseInt(objects[1].toString())==request.getStud_id()){
                 quizEntity.setAttempt(true);
             }else{
                 quizEntity.setAttempt(false);
             }
         });

//         int val = query.getFirstResult();
//         System.out.println("--------------"+val);

////         SaveResultModel result =
////
////                 resultRepository.find("inst_id=?1 and quiz_id=?2 and stud_id =?3",
////                 request.getInst_id(),
////                 quizModel.getQuiz_id().longValue(),request.getStud_id()).firstResult();
////
//         if(val==0){
//             quizEntity.setAttempt(true);
//         }else{
//             quizEntity.setAttempt(false);
//         }


         quizList.add(quizEntity);

     });

     GetQuizResponse getQuizResponse = new GetQuizResponse();
     getQuizResponse.setArrayList(quizList);
     getQuizResponse.setErrorCode(0);
     getQuizResponse.setMessage("Get Quiz");
     getQuizResponse.setStatus(true);

     return  getQuizResponse;

    }


}
