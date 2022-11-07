package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.QuestionOption;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

@ApplicationScoped
public class Quiz_Question_Option_Repository implements PanacheRepository<QuestionOption> {

//    public ArrayList getOptions(long questionID){
//
//      ArrayList<Question_Option_Model> arrayList = (ArrayList<Question_Option_Model>) list("question_id",questionID);
//      Collections.shuffle(arrayList,new Random());
//      ArrayList <Question_Option_Model> list = arrayList;
//
//      return list;
//    }


}
