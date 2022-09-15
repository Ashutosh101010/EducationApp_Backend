package com.aurd.Student.Controller;


import com.aurd.Student.Model.Request.PurchaseTestSeriesRequest;
import com.aurd.Student.Model.Response.GeneralResponse;
import com.aurd.Student.Repository.PurchaseTestRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;

@Path("/testPurchase")

public class PurchaseTestSeriesController {
    @Inject
    PurchaseTestRepository repository;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)


    public GeneralResponse addPurchase(PurchaseTestSeriesRequest request) throws ParseException {



        repository.buyTestSeries(request);


        GeneralResponse response = new GeneralResponse();
        response.seterrorCode(0);
        response.setStatus(true);
        response.setMessage("Purchase Test series Successfully");


        return response;
    }
}
