package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.TestSeriesQuestion;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestSeriesQuestionRepository implements PanacheRepository<TestSeriesQuestion> {

    public TestSeriesQuestion getQuestions(long question_id){

        return  find("question_id ",question_id).firstResult();

    }

    public ArrayList<TestSeriesQuestion> getQuestion(List<Long> ids)

    {

        ArrayList arrayList= new ArrayList<>(getEntityManager().createQuery("select TestSeriesQuestion  from TestSeriesQuestion TestSeriesQuestion where TestSeriesQuestion.question_id in :ids",TestSeriesQuestion.class)
                .setParameter("ids",ids).getResultList());
        System.out.println(arrayList);

        return arrayList;
    }


}
