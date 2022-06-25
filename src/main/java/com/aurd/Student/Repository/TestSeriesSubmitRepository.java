package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.TestSeriesSubmitModel;
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


    public ArrayList<TestSeriesSubmitModel> getStudentPracticeTestResult(int instID, long studID, long quizID){
        ArrayList<TestSeriesSubmitModel> arrayList = (ArrayList<TestSeriesSubmitModel>)
                list("quiz_id =?1 and inst_id = ?2 and stud_id = ?3",quizID,instID,studID);
        return  arrayList;

    }


}
