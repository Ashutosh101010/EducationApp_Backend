package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.TestSeriesSubmitModel;
import com.aurd.Student.Model.Request.QuizSubmitRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ApplicationScoped
public class TestSeriesSubmitRepository implements PanacheRepository<TestSeriesSubmitModel> {

    @Transactional
    public boolean submitStudentQuizResponses(QuizSubmitRequest request){

        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar now = Calendar.getInstance();
            System.out.println(sdf.format(now.getTime()));
            for(int i=0;i<request.getArrayList().size();i++){

                TestSeriesSubmitModel testSeriesSubmitModel = new Gson().fromJson(
                        new Gson().toJson(request.getArrayList().get(i)),TestSeriesSubmitModel.class);

                System.out.println(new Gson().toJson(testSeriesSubmitModel));

                testSeriesSubmitModel.setAdded_on(Timestamp.valueOf(sdf.format(now.getTime())));

                if(request.getArrayList().get(i).getSubjectId()==null){
                    testSeriesSubmitModel.setSubjectId(0);
                }else{
                    testSeriesSubmitModel.setSubjectId(request.getArrayList().get(i).getSubjectId());
                }

                if(request.getArrayList().get(i).getSubject()==null){
                    testSeriesSubmitModel.setSubject("");
                }else{
                    testSeriesSubmitModel.setSubject(request.getArrayList().get(i).getSubject());
                }



                persistAndFlush(testSeriesSubmitModel);
                System.out.println("After persisted "+new Gson().toJson(testSeriesSubmitModel));
//                getEntityManager().flush();
            }

            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }


    }


    public List<TestSeriesSubmitModel> getStudentPracticeTestResults(int instID, long studID, List<Long> quizIDs){


        Query query=getEntityManager().createQuery("select TestSeriesSubmitModel from TestSeriesSubmitModel TestSeriesSubmitModel where TestSeriesSubmitModel.stud_id=:studId and TestSeriesSubmitModel.inst_id=:instId and  TestSeriesSubmitModel.quiz_id in :ids ");
       query.setParameter("instId",instID);
       query.setParameter("ids",quizIDs);
       query.setParameter("studId",studID);

       return query.getResultList();

    }


}
