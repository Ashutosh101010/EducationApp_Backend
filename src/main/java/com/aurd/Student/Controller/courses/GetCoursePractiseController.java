package com.aurd.Student.Controller.courses;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Request.testseries.Get_PractiseTestSeries_Request;
import com.aurd.Student.Model.Response.GetCourse_PractiseTest_Response;
import com.aurd.Student.Repository.QuizRepository;
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

@Path("/courses/getPractiseTest")
public class GetCoursePractiseController {


    @Inject
    QuizRepository quizRepository;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    public GetCourse_PractiseTest_Response getPractiseTest(Get_PractiseTestSeries_Request request){

        System.out.println(new Gson().toJson(request));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();

        ArrayList<Object[]> arrayList = null;
        ArrayList<QuizModel> list = new ArrayList<>();
         if(request.getFilter().equals("")|| request.getFilter().isEmpty()){

             String lastId = "";
             if(request.getLastId()==null||request.getLastId().equals("")){
                 lastId = sdf.format(calendar.getTime());
             }else{
                 lastId = request.getLastId();
             }

            String string = "SELECT quiz_master.quiz_id,quiz_master.course_id,quiz_master.title,quiz_master.discription," +
                    " quiz_master.pic,quiz_master.price,quiz_master.test_start,quiz_master.test_duration," +
                    " quiz_master.is_active,quiz_master.marks_per_ques,quiz_master.total_ques," +
                    "quiz_master.cutoff,quiz_master.instruction,quiz_master.time,quiz_master.negative_marking," +
                    "quiz_master.quiz_type,quiz_master.test_end FROM `quiz_master`" +
                    " WHERE quiz_master.course_id = ? AND quiz_master.type =? AND quiz_master.inst_id =? AND quiz_master.time <? ORDER BY time DESC ";

            Query query = quizRepository.getEntityManager().createNativeQuery(string);

            query.setParameter(1,request.getCourse_id());
            query.setParameter(2,"Monthly Test");
            query.setParameter(3,request.getInst_id());
            query.setParameter(4,lastId);

           arrayList  = (ArrayList<Object[]>) query.setMaxResults(5).getResultList();
           arrayList.forEach(objects -> {
               QuizModel model = new QuizModel();
               model.setQuiz_id(Long.parseLong(objects[0].toString()));
               model.setCourse_id(Integer.parseInt(objects[1].toString()));
               model.setTitle(objects[2].toString());
               model.setDiscription(objects[3].toString());
               model.setPic(objects[4].toString());
               model.setPrice(objects[5].toString());
               model.setTest_start(Timestamp.valueOf(objects[6].toString()));
               model.setTest_duration(objects[7].toString());
               model.setIs_active(Integer.parseInt(objects[8].toString()));
               model.setMarks_per_ques(Integer.parseInt(objects[9].toString()));
               model.setTotal_ques(Integer.parseInt(objects[10].toString()));
               model.setCutoff(Long.parseLong(objects[11].toString()));
               model.setInstruction(objects[12].toString());
               model.setTime(Timestamp.valueOf(objects[13].toString()));
               model.setNegative_marking(objects[14].toString());
//               model.setQuiz_type(objects[15].toString());
               model.setTest_end(objects[16].toString());


               list.add(model);
           });


        }else{
            if(request.getFilter().equals("free")){


                String lastId = "";
                if(request.getLastId()==null || request.getLastId().equals("")){
                    lastId = sdf.format(calendar.getTime());
                }else{
                    lastId = request.getLastId();
                }

                String string = "SELECT quiz_master.quiz_id,quiz_master.course_id,quiz_master.title,quiz_master.discription," +
                        " quiz_master.pic,quiz_master.price,quiz_master.test_start,quiz_master.test_duration," +
                        " quiz_master.is_active,quiz_master.marks_per_ques,quiz_master.total_ques," +
                        "quiz_master.cutoff,quiz_master.instruction,quiz_master.time,quiz_master.negative_marking," +
                        "quiz_master.quiz_type,quiz_master.test_end FROM `quiz_master`" +
                        " WHERE quiz_master.course_id = ? AND quiz_master.type =? AND quiz_master.price = ?AND quiz_master.inst_id =? AND quiz_master.time <? ORDER BY time DESC ";

                Query query = quizRepository.getEntityManager().createNativeQuery(string);

                query.setParameter(1,request.getCourse_id());
                query.setParameter(2,"Monthly Test");
                query.setParameter(3,'0');
                query.setParameter(4,request.getInst_id());
                query.setParameter(5,lastId);

                arrayList  = (ArrayList<Object[]>) query.setMaxResults(5).getResultList();
                arrayList.forEach(objects -> {
                    QuizModel model = new QuizModel();
                    model.setQuiz_id(Long.parseLong(objects[0].toString()));
                    model.setCourse_id(Integer.parseInt(objects[1].toString()));
                    model.setTitle(objects[2].toString());
                    model.setDiscription(objects[3].toString());
                    model.setPic(objects[4].toString());
                    model.setPrice(objects[5].toString());
                    model.setTest_start(Timestamp.valueOf(objects[6].toString()));
                    model.setTest_duration(objects[7].toString());
                    model.setIs_active(Integer.parseInt(objects[8].toString()));
                    model.setMarks_per_ques(Integer.parseInt(objects[9].toString()));
                    model.setTotal_ques(Integer.parseInt(objects[10].toString()));
                    model.setCutoff(Long.parseLong(objects[11].toString()));
                    model.setInstruction(objects[12].toString());
                    model.setTime(Timestamp.valueOf(objects[13].toString()));
                    model.setNegative_marking(objects[14].toString());
//                    model.setQuiz_type(objects[15].toString());
                    model.setTest_end(objects[16].toString());


                    list.add(model);
                });
            }
            else{

                String lastId = "";
                if(request.getLastId()==null || request.getLastId().equals("")){
                    lastId = sdf.format(calendar.getTime());
                }else{
                    lastId = request.getLastId();
                }

                String string = "SELECT quiz_master.quiz_id,quiz_master.course_id,quiz_master.title,quiz_master.discription," +
                        " quiz_master.pic,quiz_master.price,quiz_master.test_start,quiz_master.test_duration," +
                        " quiz_master.is_active,quiz_master.marks_per_ques,quiz_master.total_ques," +
                        "quiz_master.cutoff,quiz_master.instruction,quiz_master.time,quiz_master.negative_marking," +
                        "quiz_master.quiz_type,quiz_master.test_end FROM `quiz_master` " +
                        " WHERE quiz_master.course_id = ? AND quiz_master.type =? AND quiz_master.inst_id =? AND quiz_master.price>'0' AND quiz_master.time <? ORDER BY time DESC ";

                Query query = quizRepository.getEntityManager().createNativeQuery(string);

                query.setParameter(1,request.getCourse_id());
                query.setParameter(2,"Monthly Test");
                query.setParameter(3,request.getInst_id());
                query.setParameter(4,lastId);

                arrayList  = (ArrayList<Object[]>) query.setMaxResults(5).getResultList();
                arrayList.forEach(objects -> {
                    QuizModel model = new QuizModel();
                    model.setQuiz_id(Long.parseLong(objects[0].toString()));
                    model.setCourse_id(Integer.parseInt(objects[1].toString()));
                    model.setTitle(objects[2].toString());
                    model.setDiscription(objects[3].toString());
                    model.setPic(objects[4].toString());
                    model.setPrice(objects[5].toString());
                    model.setTest_start(Timestamp.valueOf(objects[6].toString()));
                    model.setTest_duration(objects[7].toString());
                    model.setIs_active(Integer.parseInt(objects[8].toString()));
                    model.setMarks_per_ques(Integer.parseInt(objects[9].toString()));
                    model.setTotal_ques(Integer.parseInt(objects[10].toString()));
                    model.setCutoff(Long.parseLong(objects[11].toString()));
                    model.setInstruction(objects[12].toString());
                    model.setTime(Timestamp.valueOf(objects[13].toString()));
                    model.setNegative_marking(objects[14].toString());
//                    model.setQuiz_type(objects[15].toString());
                    model.setTest_end(objects[16].toString());


                    list.add(model);
                });
            }
        }




        GetCourse_PractiseTest_Response response = new GetCourse_PractiseTest_Response();
        response.setPractiseTestList(list);
        response.setMessage("Get Practise Test Success");
        response.setErrorCode(0);
        response.setStatus(true);



        return response;
    }

}
