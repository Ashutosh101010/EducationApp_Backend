package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Result;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.ArrayList;

@ApplicationScoped
public class ResultRepository implements PanacheRepository<Result> {



//    public ArrayList getResultList(long quizId,long instId){
//        Query query = getEntityManager().createQuery("select SaveResultModel from  SaveResultModel  " +
//                "SaveResultModel left join StudentModel StudentModel on" +
//                " SaveResultModel.stud_id= StudentModel.id where " +
//                "SaveResultModel.quiz_id=:quizId and " +
//                "SaveResultModel.inst_id=:instId order by" +
//                " SaveResultModel.marks_obtained DESC");
//        query.setParameter("quizId",quizId);
//        query.setParameter("instId",instId);
//        return (ArrayList<SaveResultModel>) query.getResultList();
//    }
}
