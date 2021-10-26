package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Quiz_Submit_Model;
import com.aurd.Student.Model.Request.QuizSubmitRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@ApplicationScoped
public class Quiz_Submit_Repository implements PanacheRepository<Quiz_Submit_Model> {

    @Transactional
    public boolean submitStudentQuizResponses(QuizSubmitRequest request){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar now = Calendar.getInstance();
            System.out.println(sdf.format(now.getTime()));
            for(int i=0;i<request.getArrayList().size();i++){

                Quiz_Submit_Model quiz_submit_model = new Gson().fromJson(
                        new Gson().toJson(request.getArrayList().get(i)),Quiz_Submit_Model.class);

                System.out.println(new Gson().toJson(quiz_submit_model));

                quiz_submit_model.setAdded_on(Timestamp.valueOf(sdf.format(now.getTime())));

                if(request.getArrayList().get(i).getSubjectId()==null){
                    quiz_submit_model.setSubjectId(0);
                }else{
                    quiz_submit_model.setSubjectId(request.getArrayList().get(i).getSubjectId());
                }

                if(request.getArrayList().get(i).getSubject()==null){
                    quiz_submit_model.setSubject("");
                }else{
                    quiz_submit_model.setSubject(request.getArrayList().get(i).getSubject());
                }



                persistAndFlush(quiz_submit_model);
                System.out.println("After persisted "+new Gson().toJson(quiz_submit_model));
//                getEntityManager().flush();
            }

            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }


    }


    public ArrayList getStudentPracticeTestResult(int instID, long studID, long quizID){
        ArrayList<Quiz_Submit_Model> arrayList = (ArrayList<Quiz_Submit_Model>)
                list("quiz_id =?1 and inst_id = ?2 and stud_id = ?3",quizID,instID,studID);
        return  arrayList;

    }



}
