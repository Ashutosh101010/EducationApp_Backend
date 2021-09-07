package com.aurd.Student.Controller;

import com.aurd.Student.Model.Request.EnquiryRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.EnquiryRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Date;



import java.text.ParseException;



@Path("/enquiry")

public class EnquiryController {

   @Inject
    EnquiryRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)

    @Transactional
    public GeneralResponse addEnquiry(EnquiryRequest request) throws ParseException {
//
        long millis = System.currentTimeMillis();
        Date sqlDate = new Date(millis);
        System.out.println("SQL Date ======== "+sqlDate);
        request.setReminder(sqlDate);

        repository.Enquiry(request);


        GeneralResponse response = new GeneralResponse();
        response.setStatusCode(0);
        response.setStatus(true);
        response.setMessage("Enquiry Successfully");


        return response;


    }

}
