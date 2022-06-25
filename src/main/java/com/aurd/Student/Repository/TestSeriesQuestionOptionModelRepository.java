package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.Question_Option_Model;
import com.aurd.Student.Model.Entity.TestSeriesQuestionOptionModel;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@ApplicationScoped
public class TestSeriesQuestionOptionModelRepository implements PanacheRepository<TestSeriesQuestionOptionModel> {

    public ArrayList getOptions(long questionID){

        ArrayList<TestSeriesQuestionOptionModel> arrayList = (ArrayList<TestSeriesQuestionOptionModel>) list("question_id",questionID);
        Collections.shuffle(arrayList,new Random());
        ArrayList <TestSeriesQuestionOptionModel> list = arrayList;

        return list;
    }

}
