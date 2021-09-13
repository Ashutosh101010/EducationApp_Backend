package com.aurd.Student.Controller;



import com.aurd.Student.Model.Request.AddBlogCommentRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.comment.Blog_Comment_Repository;

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

@Path("/addBlogComment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)


public class AddBlogCommentController {

    @Inject
    Blog_Comment_Repository repository;

    @POST

    @Transactional

    public GeneralResponse addComment(AddBlogCommentRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        request.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        System.out.println(request);

        GeneralResponse response = new GeneralResponse();


        boolean value = repository.addStudentBlogCommentRequest(request);
        if (value) {
            response.setStatusCode(0);
            response.setStatus(true);
            response.setMessage("Comment Added");
        } else {
            response.setStatusCode(1);
            response.setStatus(false);
            response.setMessage("Comments Not Added");
        }

        return response;

    }
}
