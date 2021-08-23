package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Quiz_Question_Model;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuizQuestionRepository implements PanacheRepository<Quiz_Question_Model> {

    public Quiz_Question_Model getQuestions(long question_id){

      return  find("question_id ",question_id).firstResult();

    }






}
