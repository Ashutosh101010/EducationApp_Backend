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
        System.out.println(new Gson().toJson(request));

        ArrayList<QuizModel> arrayList = new ArrayList();

      List<QuizModel> list =  find("inst_id=?1 and type =?2",
              request.getInst_id(),request.getType()).list();

      arrayList.addAll(list);

      return  arrayList;

    }

    public ArrayList getTestSeries(GetQuizRequest request){

        ArrayList<QuizModel> arrayList = new ArrayList();

        List<QuizModel> list =  find("inst_id=?1 and type =?2",
                request.getInst_id(),request.getType()).list();

        arrayList.addAll(list);

        return  arrayList;

    }



    public ArrayList getMonthlyTest(GetQuizRequest request){

        ArrayList<QuizModel> arrayList = new ArrayList();

        List<QuizModel> list =  find("inst_id=?1 and type =?2",
                request.getInst_id(),request.getType()).list();

        arrayList.addAll(list);

        return  arrayList;

    }



    public ArrayList getAllUpdates(long instID){
        ArrayList<QuizModel> arrayList = (ArrayList<QuizModel>) list("inst_id",instID);
        return  arrayList;
    }
}
