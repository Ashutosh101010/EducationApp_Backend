package com.aurd.Student.Controller;

import com.aurd.Student.Model.BeanClass.QuizEntity;
import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.StudentCourseModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.aurd.Student.Model.Response.GetQuizResponse;
import com.aurd.Student.Repository.QuizRepository;
import com.aurd.Student.Repository.ResultRepository;
import com.aurd.Student.Repository.StudentCourseRepository;
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
import java.util.Calendar;

@Path("/institute/getPractiseTest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GetAllPractiseTestController {
    @Inject
    QuizRepository quizRepository;

    @Inject
    ResultRepository resultRepository;

    @Inject
    StudentCourseRepository studentCourseRepository;


    @POST
    @Transactional

    public GetQuizResponse getPractiseTest(GetQuizRequest request){
        ArrayList<QuizModel> arrayList =   quizRepository.getQuizzes(request);
        ArrayList<QuizEntity> quizList = new ArrayList<>();

        System.out.println(arrayList.size());


        arrayList.forEach(quizModel -> {
            System.out.println("From Database"+quizModel.getTest_start());


            Calendar calendar = Calendar.getInstance();
           calendar.setTimeInMillis(quizModel.getTest_start().getTime());

            System.out.println("Calender time "+calendar.getTime());




            QuizEntity quizEntity = new Gson().fromJson(new Gson().toJson(quizModel),QuizEntity.class);

            System.out.println(quizEntity.getTest_start());

            String string = "SELECT * FROM `result` WHERE inst_id= ? and stud_id = ? and quiz_id=?";
            Query query = resultRepository.getEntityManager().createNativeQuery(string);
            query.setParameter(1, request.getInst_id());
            query.setParameter(2,request.getStud_id());
            query.setParameter(3,quizModel.getQuiz_id());
            Integer course = quizModel.getCourse_id();
            StudentCourseModel scm = studentCourseRepository.find("courseId=?1 and userId=?2",
                    course.longValue(),request.getStud_id()).firstResult();
            if(scm!=null){
                quizEntity.setPurchased(true);
            }else{
                quizEntity.setPurchased(false);
            }

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
            quizList.add(quizEntity);
        });


        GetQuizResponse getQuizResponse = new GetQuizResponse();
        getQuizResponse.setArrayList(quizList);
        getQuizResponse.setErrorCode(0);
        getQuizResponse.setMessage("Get Practise Test Successfully");
        getQuizResponse.setStatus(true);

        return  getQuizResponse;

    }

}
