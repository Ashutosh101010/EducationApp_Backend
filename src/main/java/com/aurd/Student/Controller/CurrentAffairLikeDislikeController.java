package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.Current_AffairsLiked_Model;
import com.aurd.Student.Model.Request.CurrentAffairLikeDislikeRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.CurrentAffairLikeDislikeRepository;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static io.quarkus.hibernate.orm.panache.Panache.getEntityManager;

@Path("/currentAffairLikeDislike")

public class CurrentAffairLikeDislikeController {

@Inject
    CurrentAffairLikeDislikeRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public GeneralResponse addCurrentAffairLike(CurrentAffairLikeDislikeRequest request) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        request.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        System.out.println(request);


        GeneralResponse response = new GeneralResponse();

        Current_AffairsLiked_Model model = new Current_AffairsLiked_Model();

        if (request.getOperation() == 1) {
            model.setCurrent_affair_id(request.getCurrent_affair_id());
            model.setAdded_by(request.getAdded_by());
            model.setAdded_on(request.getAdded_on());
            repository.persist(model);

            response.setMessage("Post Liked");
            response.setStatus(true);
            response.setStatusCode(0);

        } else if (request.getOperation() == 2) {

            String dislike = "DELETE FROM current_affairs_liked WHERE id=?";

            Query query = getEntityManager().createNativeQuery(dislike);
            query.setParameter(1,request.getId());

            query.executeUpdate();


            response.setMessage("Post Dislike");
            response.setStatus(true);
            response.setStatusCode(0);
        } else {
            response.setMessage("Unable to proceed");
            response.setStatus(false);
            response.setStatusCode(1);
        }

        return response;

    }

}
