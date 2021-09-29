package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.SaveResultModel;
import com.aurd.Student.Model.Request.SaveResultRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class SaveQuizResultRepository implements PanacheRepository<SaveResultModel> {


   //  public boolean saveResult(SaveResultRequest request) {

   //      SaveResultModel saveResultModel = new Gson().fromJson(new Gson().toJson(request), SaveResultModel.class);
   //      persist(saveResultModel);
   //      return true;


 //   }
}
