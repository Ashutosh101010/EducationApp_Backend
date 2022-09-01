package com.aurd.Student.Controller;

import com.aurd.Student.Model.Request.BuyPhysicalBookRequest;
import com.aurd.Student.Model.Response.BuyPhysicalBookResponse;
import com.aurd.Student.Repository.BookRepository;
import com.aurd.Student.Repository.PurchaseBooksRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/purchaseBook")
public class PurchaseBook {


    @Inject
    BookRepository bookRepository;

    @Inject
    PurchaseBooksRepository purchaseBooksRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public BuyPhysicalBookResponse response(BuyPhysicalBookRequest request)
    {
        BuyPhysicalBookResponse response=new BuyPhysicalBookResponse();

        return purchaseBooksRepository.buyPhysicalBook(request);

    }
}
