package com.aurd.Student.Repository;

import com.aurd.Student.Model.BeanClass.BookMarkEntity;
import com.aurd.Student.Model.Entity.QuizModel;
import com.aurd.Student.Model.Entity.TestSeriesModel;
import com.aurd.Student.Model.Request.GetQuizRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



@ApplicationScoped
public class QuizRepository implements PanacheRepository<QuizModel> {

    public ArrayList getQuizzes(GetQuizRequest request){

        ArrayList<QuizModel> arrayList = new ArrayList();

        try {
            System.out.println(new Gson().toJson(request));


               if(request.getCourse_id()!=0 && request.getSubject_id()==0){
               arrayList = (ArrayList<QuizModel>) find("inst_id=?1 and type =?2 and course_id=?3",
                     request.getInst_id(),request.getType(),request.getCourse_id()).list();





                return  arrayList;

            }else if(request.getSubject_id()!=0 && request.getCourse_id() ==0){
                arrayList = (ArrayList<QuizModel>) find("inst_id=?1 and type =?2 and subject_id=?3",
                       request.getInst_id(),request.getType(),request.getSubject_id()).list();

                  return arrayList;

            }else if(request.getCourse_id()!=0 && request.getSubject_id()!=0){
                arrayList = (ArrayList<QuizModel>) find("inst_id=?1 and type =?2 and subject_id=?3 and course_id = ?4",
                        request.getInst_id(),request.getType(),request.getSubject_id(),request.getCourse_id()).list();

                return arrayList;

            }
               else{
                   arrayList = (ArrayList<QuizModel>) find("inst_id=?1 and type =?2 ",
                           request.getInst_id().intValue(),request.getType()).list();

                   return arrayList;
               }


        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList();
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

    public ArrayList<QuizModel> getAllTestSeries(Integer inst_id,String type) {

        ArrayList<QuizModel>arrayList=(ArrayList<QuizModel>)
                find("inst_id=?1 and type=?2",inst_id,type).list();
        return  arrayList;
    }
}
