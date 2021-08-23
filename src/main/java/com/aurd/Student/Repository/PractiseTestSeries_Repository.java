package com.aurd.Student.Repository;

import com.aurd.Student.Model.Entity.TestSeriesModel;
import com.aurd.Student.Model.Request.testseries.Submit_PracticeTest_Request;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;

@ApplicationScoped
public class PractiseTestSeries_Repository implements PanacheRepository<TestSeriesModel> {

    public ArrayList getPractiseTest(long instID, long quizID){
     ArrayList<TestSeriesModel> arrayList = (ArrayList<TestSeriesModel>)
             find("inst_id =?1 and quiz_id = ?2",instID,quizID).list();
     return  arrayList;
    }


    public  void addPracticeTest(Submit_PracticeTest_Request request){
        TestSeriesModel testSeriesModel = new Gson().fromJson(new Gson().toJson(request),TestSeriesModel.class);
        persist(testSeriesModel);
    }

    public  long getCutOff(long id){
      TestSeriesModel testSeriesModel = find("id",id).firstResult();
      return  testSeriesModel.getCutoff();
    }


}
