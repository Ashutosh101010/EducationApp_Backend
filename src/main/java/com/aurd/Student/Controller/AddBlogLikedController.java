package com.aurd.Student.Controller;


import com.aurd.Student.Model.Request.AddStudentBlogLikedRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.StudentBlogLikedRepository;


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

@Path("/addBlogLiked")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)


public class AddBlogLikedController {



    @Inject
    StudentBlogLikedRepository repository;

    @POST

    @Transactional

    public GeneralResponse addStudentLike(AddStudentBlogLikedRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        request.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        System.out.println(request);

        GeneralResponse response = new GeneralResponse();

        boolean value = repository.addStudentBlogLikedRequest(request);
        if (value) {
            response.setStatusCode(0);
            response.setStatus(true);
            response.setMessage("Likes Added");
        } else {
            response.setStatusCode(1);
            response.setStatus(false);
            response.setMessage("Likes Not Added");
        }

        return response;
    }

    }
