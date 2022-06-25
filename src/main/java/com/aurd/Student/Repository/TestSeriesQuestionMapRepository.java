package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.TestSeriesQuestionMap;
import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.ArrayList;

@ApplicationScoped
public class TestSeriesQuestionMapRepository implements PanacheRepository<TestSeriesQuestionMap> {
    public ArrayList getQuestionID(long quizID){

        ArrayList<TestSeriesQuestionMap>  list =
                (ArrayList<TestSeriesQuestionMap>) find("quiz_id",quizID).list();

//        Query query= getEntityManager().createQuery("select Quiz_Question_Map_Model from Quiz_Question_Map_Model Quiz_Question_Map_Model join SubjectModel SubjectModel on  Quiz_Question_Map_Model.subject_id =SubjectModel.id where Quiz_Question_Map_Model.quiz_id= : quizId ");
//
//
//       query.setParameter("quizId",quizID);

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
