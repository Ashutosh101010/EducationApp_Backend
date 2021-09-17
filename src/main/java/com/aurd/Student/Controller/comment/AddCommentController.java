package com.aurd.Student.Controller.comment;


import com.aurd.Student.Model.Request.AddPostCommentRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentPostCommentRepository;
import com.aurd.Student.Repository.comment.Blog_Comment_Repository;
import com.aurd.Student.Repository.comment.Current_Affair_Comment_Repository;
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

@Path("/addComment")
public class AddCommentController {
    @Inject
    Blog_Comment_Repository blogCommentRepository;


    @Inject
    Current_Affair_Comment_Repository affair_comment_repository;

    @Inject
    StudentPostCommentRepository postCommentRepository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional

    public GeneralResponse addComment(AddPostCommentRequest request){
        System.out.println(new Gson().fromJson(new Gson().toJson(request),AddPostCommentRequest.class));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        request.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        System.out.println(request);

        GeneralResponse response =  new GeneralResponse();

        if(request.getType().equals("blog")){
          boolean val =   blogCommentRepository.addStudentBlogCommentRequest(request);
            if(val==true){
                response.setStatusCode(0);
                response.setMessage("Comment added");
                response.setStatus(true);
            }else{
                response.setStatusCode(1);
                response.setMessage("Unable to add commment");
                response.setStatus(true);
            }
        }else if(request.getType().equals("currentAffair")){

           boolean val= affair_comment_repository.addCurrentAffairCommentRequest(request);
            if(val==true){
                response.setStatusCode(0);
                response.setMessage("Comment added");
                response.setStatus(true);
            }else{
                response.setStatusCode(1);
                response.setMessage("Unable to add commment");
                response.setStatus(true);
            }
        }else if(request.getType().equals("studentPost")){
           boolean val =  postCommentRepository.addPostCommentRequest(request);
            if(val==true){
                response.setStatusCode(0);
                response.setMessage("Comment added");
                response.setStatus(true);
            }else{
                response.setStatusCode(1);
                response.setMessage("Unable to add commment");
                response.setStatus(true);
            }
        }else if(request.getType().equals("notes")){

        }
        return response;

    }

}
