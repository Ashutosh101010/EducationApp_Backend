package com.aurd.Student.Controller.comment;


import com.aurd.Student.Model.Request.CommentReplyRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.CommentReplyRepository;
import com.google.gson.Gson;

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

@Path("addCommentReply")

public class CommentReplyController {

    @Inject
    CommentReplyRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional


    public GeneralResponse CommentReply(CommentReplyRequest request) {

        System.out.println(new Gson().toJson(request));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        request.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        System.out.println(request);

        GeneralResponse response = new GeneralResponse();

        boolean value = repository.addCommentReplyRequest(request);
        if (value) {
            response.seterrorCode(0);
            response.setStatus(true);
            response.setMessage("Comment Reply Added");
        } else {
            response.seterrorCode(1);
            response.setStatus(false);
            response.setMessage("Comments Reply Not Added");
        }

        return response;


    }

    }
