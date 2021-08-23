package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.map.Quiz_Question_Map_Model;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Get_QuestionID_Repository implements PanacheRepository<Quiz_Question_Map_Model> {


    public ArrayList getQuestionID(long quizID){

        ArrayList<Quiz_Question_Map_Model>  list =
                (ArrayList<Quiz_Question_Map_Model>) find("quiz_id",quizID).list();



        return  list;

    }


}
