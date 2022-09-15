package com.aurd.Student.Controller;

import com.aurd.Student.Model.Request.PurchaseAudioBookRequest;
import com.aurd.Student.Model.Response.PurchaseAudioBookResponse;
import com.aurd.Student.Repository.PurchaseAudioBookRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/purchaseAudioBook")
public class PurchaseAudioBook {

    @Inject
    PurchaseAudioBookRepository repository;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public PurchaseAudioBookResponse purchaseAudioBook(PurchaseAudioBookRequest request)
    {

        return  repository.purchaseAudioBook(request);
    }
}
