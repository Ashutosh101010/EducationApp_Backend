package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.SaveResultModel;
import com.aurd.Student.Model.Request.testseries.Get_PractiseTestSeries_Request;
import com.aurd.Student.Model.Response.GetCourse_PractiseTest_Response;
import com.aurd.Student.Repository.QuizRepository;
import com.aurd.Student.Repository.ResultRepository;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Path("/courses/getPractiseTest")
public class GetCoursePractiseController {


    @Inject
    QuizRepository quizRepository;


    @Inject
    ResultRepository resultRepository;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public GetCourse_PractiseTest_Response getPractiseTest(Get_PractiseTestSeries_Request request){

        System.out.println(new Gson().toJson(request));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();

//        ArrayList<Object[]> arrayList = null;
        ArrayList<QuizModel> list = new ArrayList<>();
         if(request.getFilter().equals("")|| request.getFilter().isEmpty()){

//             String lastId = "";
//             if(request.getLastId()==null||request.getLastId().equals("")){
//                 lastId = sdf.format(calendar.getTime());
//             }else{
//                 lastId = request.getLastId();
//             }

//            String string = "SELECT quiz_master.quiz_id,quiz_master.course_id,quiz_master.title,quiz_master.discription," +
//                    " quiz_master.pic,quiz_master.price,quiz_master.test_start,quiz_master.test_duration," +
//                    " quiz_master.is_active,quiz_master.marks_per_ques,quiz_master.total_ques," +
//                    "quiz_master.cutoff,quiz_master.instruction,quiz_master.time,quiz_master.negative_marking," +
//                    "quiz_master.quiz_type,quiz_master.test_end FROM `quiz_master`" +
//                    " WHERE quiz_master.course_id = ? AND quiz_master.type =? AND quiz_master.inst_id =? AND quiz_master.time <? ORDER BY time DESC ";

         String string="select QuizModel from QuizModel QuizModel where QuizModel.course_id=:courseId and QuizModel.type=:type and QuizModel.inst_id=:instId order by QuizModel.time desc ";
        Query query = quizRepository.getEntityManager().createQuery(string);

            query.setParameter("courseId",request.getCourse_id());
            query.setParameter("type","Monthly Test");
            query.setParameter("instId",request.getInst_id());
//            query.setParameter("time",lastId);

            System.out.println(query.toString());
           list  = (ArrayList<QuizModel>) query.getResultList();


        }else{
            if(request.getFilter().equals("free")){


//                String lastId = "";
//                if(request.getLastId()==null || request.getLastId().equals("")){
//                    lastId = sdf.format(calendar.getTime());
//                }else{
//                    lastId = request.getLastId();
//                }

                String string="select QuizModel from QuizModel QuizModel where QuizModel.course_id=:courseId and QuizModel.test_type=:testType and QuizModel.type=:type and QuizModel.inst_id=:instId  order by QuizModel.time desc ";
                Query query = quizRepository.getEntityManager().createQuery(string);

                query.setParameter("courseId",request.getCourse_id());
                query.setParameter("type","Monthly Test");
                query.setParameter("instId",request.getInst_id());
//                query.setParameter("time",lastId);
                query.setParameter("testType","free");

                list  = (ArrayList<QuizModel>) query.getResultList();


            }
            else{

//                String lastId = "";
//                if(request.getLastId()==null || request.getLastId().equals("")){
//                    lastId = sdf.format(calendar.getTime());
//                }else{
//                    lastId = request.getLastId();
//                }


                String string="select QuizModel from QuizModel QuizModel where QuizModel.course_id=:courseId and QuizModel.type=:type and QuizModel.price>:price and QuizModel.inst_id=:instId  order by QuizModel.time desc ";
                Query query = quizRepository.getEntityManager().createQuery(string);

                query.setParameter("courseId",request.getCourse_id());
                query.setParameter("type","Monthly Test");
                query.setParameter("instId",request.getInst_id());
//                query.setParameter("time",lastId);
                query.setParameter("price",0);

                list  = (ArrayList<QuizModel>) query.getResultList();


            }
        }



         list.forEach(quizModel -> {
             String string = "SELECT * FROM `result` WHERE inst_id= ? and stud_id = ? and quiz_id=?";
             Query query = resultRepository.getEntityManager().createNativeQuery(string);
             query.setParameter(1, request.getInst_id());
             query.setParameter(2,request.getUser_id());
             query.setParameter(3,quizModel.getQuiz_id());

             List<SaveResultModel> result=query.getResultList();
             if(result.isEmpty())
             {
                 quizModel.setAttempt(false);
             }
             else{
                 quizModel.setAttempt(true);
             }
         });

        GetCourse_PractiseTest_Response response = new GetCourse_PractiseTest_Response();
        response.setPractiseTestList(list);
        response.setMessage("Get Practise Test Success");
        response.setErrorCode(0);
        response.setStatus(true);



        return response;
    }

}
