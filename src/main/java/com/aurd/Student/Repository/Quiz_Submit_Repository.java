package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Quiz_Submit_Model;
import com.aurd.Student.Model.Request.QuizSubmitRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class Quiz_Submit_Repository implements PanacheRepository<Quiz_Submit_Model> {

    public boolean submitStudentQuizResponses(QuizSubmitRequest request){

        for(int i=0;i<request.getArrayList().size();i++){
            Quiz_Submit_Model quiz_submit_model = new Gson().fromJson(
                    new Gson().toJson(request.getArrayList().get(i)),Quiz_Submit_Model.class);

            persist(quiz_submit_model);
        }

        return  true;

    }


    public ArrayList getStudentPracticeTestResult(int instID, long studID, long quizID){
        ArrayList<Quiz_Submit_Model> arrayList = (ArrayList<Quiz_Submit_Model>)
                list("quiz_id =?1 and inst_id = ?2 and stud_id = ?3",quizID,instID,studID);
        return  arrayList;

    }

}
