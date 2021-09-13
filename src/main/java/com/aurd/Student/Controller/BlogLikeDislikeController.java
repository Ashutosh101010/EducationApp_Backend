package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.Blog_Liked_Model;
import com.aurd.Student.Model.Request.BlogLikeDislikeRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.BlogLikedRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Path("/blogLikeDislike")

public class BlogLikeDislikeController {

    @Inject
    BlogLikedRepository repository;
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public GeneralResponse likeDislikeBlog(BlogLikeDislikeRequest request) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        request.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        System.out.println(request);



        GeneralResponse response = new GeneralResponse();

        Blog_Liked_Model model = new Blog_Liked_Model();

        if (request.getOperation() == 1) {
            model.setBlog_id(request.getBlog_id());
            model.setAdded_by(request.getAdded_by());
            model.setAdded_on(request.getAdded_on());
            repository.persist(model);

            response.setMessage("Post Liked");
            response.setStatus(true);
            response.setStatusCode(0);

        } else if (request.getOperation() == 2) {
            model.setAdded_by(request.getAdded_by());
            model.setBlog_id(request.getBlog_id());
            repository.delete(model);

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
