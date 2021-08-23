package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Question_Option_Model;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class Quiz_Question_Option_Repository implements PanacheRepository<Question_Option_Model> {

    public ArrayList getOptions(long questionID){

      ArrayList<Question_Option_Model> arrayList = (ArrayList<Question_Option_Model>) list("question_id",questionID);
      return  arrayList;
    }


}
