package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.TestSeriesQuestionMap;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestSeriesQuestionMapRepository implements PanacheRepository<TestSeriesQuestionMap> {
    public List<TestSeriesQuestionMap> getQuestionID(ArrayList<Long> quizIDs){

        Query query=getEntityManager().createQuery("select TestSeriesQuestionMap from TestSeriesQuestionMap TestSeriesQuestionMap where TestSeriesQuestionMap.quiz_id in :ids");
       query.setParameter("ids",quizIDs);

      List<TestSeriesQuestionMap>  list = query.getResultList();

   list.forEach(testSeriesQuestionMap -> {
       System.out.println(testSeriesQuestionMap);
   });

        return  list;

    }
    public ArrayList<TestSeriesQuestionMap> getQuestion(long quizID){




//      Query query =  getEntityManager().createNativeQuery("select * from quiz_ques_map inner join subjects on " +
//                "subjects.id = quiz_ques_map.subject_id where quiz_ques_map.quiz_id =?  " );
//
//      query.setParameter(1,quizID);

        Query query= getEntityManager().createQuery("select TestSeriesQuestionMap from TestSeriesQuestionMap TestSeriesQuestionMap" +
                " left join SubjectModel SubjectModel on  TestSeriesQuestionMap.subject_id = SubjectModel.id " +
                " where TestSeriesQuestionMap.quiz_id= : quizId");


        query.setParameter("quizId",quizID);

        return (ArrayList<TestSeriesQuestionMap>) query.getResultList();

    }

}
