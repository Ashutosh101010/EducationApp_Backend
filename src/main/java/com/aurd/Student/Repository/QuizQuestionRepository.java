package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.QuizQuestion;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class QuizQuestionRepository implements PanacheRepository<QuizQuestion> {
//
//    public Quiz_Question_Model getQuestions(long question_id){
//
//      return  find("question_id ",question_id).firstResult();
//
//    }
//
//    public ArrayList<Quiz_Question_Model> getQuestion(List<Long> ids)
//
//    {
//
//       ArrayList arrayList= new ArrayList<>(getEntityManager().createQuery("select Quiz_Question_Model  from Quiz_Question_Model Quiz_Question_Model where Quiz_Question_Model.question_id in :ids",Quiz_Question_Model.class)
//                                            .setParameter("ids",ids).getResultList());
//       System.out.println(arrayList);
//
//       return arrayList;
//    }
//
//




}
