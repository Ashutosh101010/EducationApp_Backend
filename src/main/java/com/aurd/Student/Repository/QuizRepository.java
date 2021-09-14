package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class QuizRepository implements PanacheRepository<QuizModel> {

    public ArrayList getQuizzes(GetQuizRequest request){
        ArrayList<QuizModel> arrayList = new ArrayList();
        try {
            System.out.println(new Gson().toJson(request));

           arrayList = (ArrayList<QuizModel>) find("inst_id=?1 and type =?2",
                    request.getInst_id(),request.getType()).list();

            return  arrayList;
        }catch (Exception e){
            e.printStackTrace();
            return arrayList;
        }


    }

    public ArrayList getTestSeries(GetQuizRequest request){

        ArrayList<QuizModel> arrayList = new ArrayList();

        List<QuizModel> list =  find("inst_id=?1 and type =?2",
                request.getInst_id(),request.getType()).list();

        arrayList.addAll(list);

        return  arrayList;

    }



    public ArrayList getMonthlyTest(long instID,String type){

        ArrayList<QuizModel> arrayList = new ArrayList();

        List<QuizModel> list =  find("inst_id=?1 and type =?2",
               instID,type).list();

        arrayList.addAll(list);

        return  arrayList;

    }



    public ArrayList getAllUpdates(long instID){
        ArrayList<QuizModel> arrayList = (ArrayList<QuizModel>) list("inst_id",instID);
        return  arrayList;
    }
}
