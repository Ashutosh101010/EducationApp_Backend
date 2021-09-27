package com.aurd.Student.Controller;

import com.aurd.Student.Model.Entity.BookMarkModel;
import com.aurd.Student.Model.Request.AddBookmarkRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.BookMarkRepository;
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

@Path("/students/addBookmark")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AddBookmarkController {

    @Inject
    BookMarkRepository repository;
    @POST

    @Transactional
    public GeneralResponse addBookmark(AddBookmarkRequest request){

        System.out.println(new Gson().toJson(request));

        BookMarkModel bookMarkModel = new Gson().fromJson(new Gson().
                toJson(request),BookMarkModel.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();


        bookMarkModel.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));

        repository.persist(bookMarkModel);




        GeneralResponse response = new GeneralResponse();
        response.setStatusCode(0);
        response.setMessage("Add Book mark Success");
        response.setStatus(true);

        return  response;

    }


}
