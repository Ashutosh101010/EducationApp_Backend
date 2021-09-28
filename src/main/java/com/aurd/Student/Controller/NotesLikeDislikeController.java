package com.aurd.Student.Controller;


import com.aurd.Student.Model.Entity.Notes_Liked_Model;
import com.aurd.Student.Model.Request.NotesLikeDislikeRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.NotesLikeDislikeRepository;

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

@Path("/addNotesLikeDislike")


public class NotesLikeDislikeController {

   @Inject

    NotesLikeDislikeRepository repository;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public GeneralResponse likeDislikeNotes(NotesLikeDislikeRequest request) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        request.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        System.out.println(request);

        GeneralResponse response = new GeneralResponse();
        Notes_Liked_Model model = new Notes_Liked_Model();

        if (request.getOperation() == 1) {
            model.setNotes_id(request.getNotes_id());
            model.setAdded_by(request.getAdded_by());
            model.setAdded_on(request.getAdded_on());
            repository.persist(model);

            response.setMessage("Post Liked");
            response.setStatus(true);
            response.setStatusCode(0);

        } else if (request.getOperation() == 2) {

            String dislike = "DELETE FROM notes_liked WHERE notes_id=? and added_by=?";

            Query query = getEntityManager().createNativeQuery(dislike);
            query.setParameter(1,request.getNotes_id());
            query.setParameter(2,request.getAdded_by());

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
