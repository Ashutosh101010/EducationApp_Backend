package com.aurd.Student.Controller;


import com.aurd.Student.Model.Request.BuyCoursesRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.BuyCoursesRepository;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

@Path("/BuyCourse")

public class BuyCourseController {

    @Inject
    BuyCoursesRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional
    public GeneralResponse addBuyCourses(BuyCoursesRequest request) throws ParseException {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        request.setCreated_at(timestamp);
        request.setUpdated_at(timestamp);

//        Date calDate = Calendar.getInstance().getTime();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//
//        String string = dateFormat.format(calDate);
//        Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(string);
//
//        java.sql.Date  sqlDate = new java.sql.Date(utilDate.getTime());
//        request.setDue_date(sqlDate);
        repository.BuyCourses(request);


        GeneralResponse response = new GeneralResponse();
        response.setStatusCode(0);
        response.setStatus(true);
        response.setMessage("Buy Courses Successfully");


        return response;


    }


}
