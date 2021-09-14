package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.Current_AffairsLiked_Model;
import com.aurd.Student.Model.Request.CurrentAffairLikeDislikeRequest;
import com.google.gson.Gson;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class CurrentAffairLikeDislikeRepository implements PanacheRepository<Current_AffairsLiked_Model> {


    public boolean addCurrentAffairLikeDislikeRequest(CurrentAffairLikeDislikeRequest request) {

        Current_AffairsLiked_Model current_affairsLiked_model = new Gson().fromJson(new Gson().toJson(request), Current_AffairsLiked_Model.class);
        persist(current_affairsLiked_model);


        return true;
    }


}
