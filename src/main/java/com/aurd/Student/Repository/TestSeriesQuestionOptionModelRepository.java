package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.TestSeriesQuestionOption;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

@ApplicationScoped
public class TestSeriesQuestionOptionModelRepository implements PanacheRepository<TestSeriesQuestionOption> {

//    public ArrayList getOptions(long questionID){
//
//        ArrayList<TestSeriesQuestionOptionModel> arrayList = (ArrayList<TestSeriesQuestionOptionModel>) list("question_id",questionID);
//        Collections.shuffle(arrayList,new Random());
//        ArrayList <TestSeriesQuestionOptionModel> list = arrayList;
//
//        return list;
//    }

}
