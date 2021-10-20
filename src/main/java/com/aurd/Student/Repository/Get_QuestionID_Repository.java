package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Get_QuestionID_Repository implements PanacheRepository<Quiz_Question_Map_Model> {


    public ArrayList getQuestionID(long quizID){

        ArrayList<Quiz_Question_Map_Model>  list =
                (ArrayList<Quiz_Question_Map_Model>) find("quiz_id",quizID).list();

//        Query query= getEntityManager().createQuery("select Quiz_Question_Map_Model from Quiz_Question_Map_Model Quiz_Question_Map_Model join SubjectModel SubjectModel on  Quiz_Question_Map_Model.subject_id =SubjectModel.id where Quiz_Question_Map_Model.quiz_id= : quizId ");
//
//
//       query.setParameter("quizId",quizID);

        return  list;

    }
    public ArrayList getQuestion(long quizID){




//      Query query =  getEntityManager().createNativeQuery("select * from quiz_ques_map inner join subjects on " +
//                "subjects.id = quiz_ques_map.subject_id where quiz_ques_map.quiz_id =?  " );
//
//      query.setParameter(1,quizID);

        Query query= getEntityManager().createQuery("select Quiz_Question_Map_Model from Quiz_Question_Map_Model Quiz_Question_Map_Model" +
                " left join SubjectModel SubjectModel on  Quiz_Question_Map_Model.subject_id = SubjectModel.id " +
                " where Quiz_Question_Map_Model.quiz_id= : quizId");


        query.setParameter("quizId",quizID);

        return (ArrayList<Quiz_Question_Map_Model>) query.getResultList();

    }


}
