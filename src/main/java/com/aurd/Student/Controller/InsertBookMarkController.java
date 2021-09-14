package com.aurd.Student.Controller;


import com.aurd.Student.Model.Request.InsertBookMarkRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.BookMarkRepository;


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

@Path("/addBookMark")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)


public class InsertBookMarkController {

    @Inject
    BookMarkRepository repository;

    @POST

    @Transactional
    public GeneralResponse addBookMark(InsertBookMarkRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        request.setAdded_on(Timestamp.valueOf(simpleDateFormat.format(calendar.getTime())));
        System.out.println(request);

        GeneralResponse response = new GeneralResponse();


        boolean value = repository.addInsertBookMarkRequest(request);
        if (value) {
            response.setStatusCode(0);
            response.setStatus(true);
            response.setMessage("BookMark Added");
        } else {
            response.setStatusCode(1);
            response.setStatus(false);
            response.setMessage("BookMark Not Added");
        }

        return response;

    }






}
