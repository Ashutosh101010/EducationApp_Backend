package com.aurd.Student.Repository;


import com.aurd.Student.Model.Entity.CurrentAffairsLiked;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped

public class CurrentAffairLikeDislikeRepository implements PanacheRepository<CurrentAffairsLiked> {


//    public boolean addCurrentAffairLikeDislikeRequest(CurrentAffairLikeDislikeRequest request) {
//
//        Current_AffairsLiked_Model current_affairsLiked_model = new Gson().fromJson(new Gson().toJson(request), Current_AffairsLiked_Model.class);
//        persist(current_affairsLiked_model);
//
//
//        return true;
//    }


}
