package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.SaveResultModel;
import com.aurd.Student.Model.Entity.TestSeriesResult;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.ArrayList;

@ApplicationScoped
public class TestSeriesResultRepository implements PanacheRepository<TestSeriesResult> {


    public ArrayList getResultList(long quizId, long instId){
        Query query = getEntityManager().createQuery("select TestSeriesResult from  TestSeriesResult  " +
                "TestSeriesResult left join StudentModel StudentModel on" +
                " TestSeriesResult.stud_id= StudentModel.id where " +
                "TestSeriesResult.quiz_id=:quizId and " +
                "TestSeriesResult.inst_id=:instId order by" +
                " TestSeriesResult.marks_obtained DESC");
        query.setParameter("quizId",quizId);
        query.setParameter("instId",instId);
        return (ArrayList<TestSeriesResult>) query.getResultList();
    }
}
